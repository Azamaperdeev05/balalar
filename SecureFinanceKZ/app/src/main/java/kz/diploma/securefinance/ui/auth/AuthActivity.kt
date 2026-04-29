package kz.diploma.securefinance.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.R
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.ui.MainActivity
import kz.diploma.securefinance.util.enableFlagSecure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthActivity : AppCompatActivity() {

    private lateinit var tilPin: TextInputLayout
    private lateinit var etPin: TextInputEditText
    private lateinit var btnUnlock: MaterialButton
    private lateinit var btnBiometric: MaterialButton
    private lateinit var tvError: TextView
    private lateinit var progressBar: ProgressBar

    private var failedAttempts = 0
    private var lockTimer: CountDownTimer? = null
    private var isLocked = false

    // Экспоненциалды кешіктіру: 5-інші қатедан бастап
    private val lockDelays = longArrayOf(30_000, 60_000, 120_000, 300_000, 600_000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableFlagSecure()
        setContentView(R.layout.activity_auth)

        tilPin = findViewById(R.id.tilPin)
        etPin = findViewById(R.id.etPin)
        btnUnlock = findViewById(R.id.btnUnlock)
        btnBiometric = findViewById(R.id.btnBiometric)
        tvError = findViewById(R.id.tvError)
        progressBar = findViewById(R.id.progressBar)

        // Жүйелік klaviaturany жасыру — custom keypad пайдаланамыз
        etPin.showSoftInputOnFocus = false

        btnUnlock.setOnClickListener { attemptPinUnlock() }
        btnBiometric.setOnClickListener { attemptBiometricUnlock() }
        setupKeypad()

        val app = application as FinanceApp
        val showBio = app.biometricHelper.isBiometricAvailable(this)
                && app.biometricHelper.isBiometricEnabled
                && !app.biometricHelper.isBiometricKeyInvalidated()
        btnBiometric.visibility = if (showBio) View.VISIBLE else View.GONE
        if (showBio) attemptBiometricUnlock()
    }

    private fun setupKeypad() {
        val digitIds = listOf(
            R.id.btnKey0 to "0", R.id.btnKey1 to "1", R.id.btnKey2 to "2",
            R.id.btnKey3 to "3", R.id.btnKey4 to "4", R.id.btnKey5 to "5",
            R.id.btnKey6 to "6", R.id.btnKey7 to "7", R.id.btnKey8 to "8",
            R.id.btnKey9 to "9"
        )
        digitIds.forEach { (id, digit) ->
            findViewById<View>(id)?.setOnClickListener {
                val cur = etPin.text?.toString() ?: ""
                if (cur.length < 8) etPin.setText(cur + digit)
            }
        }
        findViewById<View>(R.id.btnKeyBackspace)?.setOnClickListener {
            val cur = etPin.text?.toString() ?: ""
            if (cur.isNotEmpty()) etPin.setText(cur.dropLast(1))
        }
        findViewById<View>(R.id.btnKeyConfirm)?.setOnClickListener { attemptPinUnlock() }
    }

    private fun attemptPinUnlock() {
        if (isLocked) return
        val pin = etPin.text?.toString() ?: ""
        if (pin.isEmpty()) return

        setLoading(true)

        lifecycleScope.launch {
            try {
                val app = application as FinanceApp
                val dbKey = withContext(Dispatchers.Default) {
                    app.cryptoManager.verifyPin(pin.toCharArray())
                }

                if (dbKey != null) {
                    withContext(Dispatchers.IO) { app.openDatabase(dbKey) }
                    SessionManager.unlock(dbKey)
                    dbKey.fill(0)
                    failedAttempts = 0
                    // CLEAR_TASK: AuthActivity back stack-тен жойылады → артқа басса loop болмайды
                    startActivity(Intent(this@AuthActivity, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                    finish()
                } else {
                    setLoading(false)
                    onWrongPin()
                }
            } catch (e: Exception) {
                setLoading(false)
                showError("Қате: ${e.message}")
            }
        }
    }

    private fun attemptBiometricUnlock() {
        val app = application as FinanceApp
        val cipher = app.biometricHelper.getDecryptCipher() ?: return
        app.biometricHelper.authenticate(
            activity = this,
            cipher = cipher,
            title = getString(R.string.auth_biometric_title),
            subtitle = getString(R.string.auth_biometric_subtitle),
            negativeText = getString(R.string.auth_biometric_cancel),
            onSuccess = { decCipher ->
                val dbKey = app.biometricHelper.decryptDbKey(decCipher)
                if (dbKey != null) {
                    lifecycleScope.launch {
                        try {
                            withContext(Dispatchers.IO) { app.openDatabase(dbKey) }
                            SessionManager.unlock(dbKey)
                            dbKey.fill(0)
                            startActivity(Intent(this@AuthActivity, MainActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            })
                            finish()
                        } catch (e: Exception) {
                            showError("Қате: ${e.message}")
                        }
                    }
                } else {
                    showError(getString(R.string.auth_wrong_pin))
                }
            },
            onError = { msg -> showError(msg) }
        )
    }

    private fun onWrongPin() {
        failedAttempts++
        etPin.text?.clear()
        tilPin.error = " "

        if (failedAttempts >= 10) {
            showError(getString(R.string.auth_locked_warning))
        } else if (failedAttempts >= 5) {
            val idx = (failedAttempts - 5).coerceAtMost(lockDelays.size - 1)
            startLockTimer(lockDelays[idx])
        } else {
            val left = 5 - failedAttempts
            showError("${getString(R.string.auth_wrong_pin)} (${getString(R.string.auth_attempts_left, left)})")
        }
    }

    private fun startLockTimer(ms: Long) {
        isLocked = true
        btnUnlock.isEnabled = false
        lockTimer?.cancel()
        lockTimer = object : CountDownTimer(ms, 1000) {
            override fun onTick(left: Long) {
                val s = left / 1000
                val txt = if (s >= 60) "${s / 60} мин ${s % 60} с" else "$s с"
                showError(getString(R.string.auth_locked, txt))
            }
            override fun onFinish() {
                isLocked = false
                btnUnlock.isEnabled = true
                tvError.visibility = View.GONE
                tilPin.error = null
            }
        }.start()
    }

    private fun showError(msg: String) {
        tvError.text = msg
        tvError.visibility = View.VISIBLE
    }

    private fun setLoading(on: Boolean) {
        progressBar.visibility = if (on) View.VISIBLE else View.GONE
        btnUnlock.isEnabled = !on && !isLocked
        etPin.isEnabled = !on
    }

    override fun onDestroy() {
        super.onDestroy()
        lockTimer?.cancel()
    }
}
