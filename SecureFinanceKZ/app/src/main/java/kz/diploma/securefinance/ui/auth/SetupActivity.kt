package kz.diploma.securefinance.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
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
import kz.diploma.securefinance.util.DefaultCategories
import kz.diploma.securefinance.util.enableFlagSecure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SetupActivity : AppCompatActivity() {

    private lateinit var etPin: TextInputEditText
    private lateinit var etConfirmPin: TextInputEditText
    private lateinit var btnSetup: MaterialButton
    private lateinit var progressBar: ProgressBar
    private lateinit var tilPin: TextInputLayout
    private lateinit var tilConfirmPin: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableFlagSecure()
        setContentView(R.layout.activity_setup)
        etPin = findViewById(R.id.etPin)
        etConfirmPin = findViewById(R.id.etConfirmPin)
        btnSetup = findViewById(R.id.btnSetup)
        progressBar = findViewById(R.id.progressBar)
        tilPin = findViewById(R.id.tilPin)
        tilConfirmPin = findViewById(R.id.tilConfirmPin)
        btnSetup.setOnClickListener { attemptSetup() }

        // Жүйелік klaviaturany жасыру — custom keypad пайдаланамыз
        etPin.showSoftInputOnFocus = false
        etConfirmPin.showSoftInputOnFocus = false

        etPin.requestFocus()
        setupKeypad()
    }

    private fun activeField() = if (etConfirmPin.isFocused) etConfirmPin else etPin

    private fun setupKeypad() {
        val digitIds = listOf(
            R.id.btnKey0 to "0", R.id.btnKey1 to "1", R.id.btnKey2 to "2",
            R.id.btnKey3 to "3", R.id.btnKey4 to "4", R.id.btnKey5 to "5",
            R.id.btnKey6 to "6", R.id.btnKey7 to "7", R.id.btnKey8 to "8",
            R.id.btnKey9 to "9"
        )
        digitIds.forEach { (id, digit) ->
            findViewById<View>(id)?.setOnClickListener {
                val field = activeField()
                val cur = field.text?.toString() ?: ""
                if (cur.length < 8) field.setText(cur + digit)
            }
        }
        findViewById<View>(R.id.btnKeyBackspace)?.setOnClickListener {
            val field = activeField()
            val cur = field.text?.toString() ?: ""
            if (cur.isNotEmpty()) field.setText(cur.dropLast(1))
        }
        findViewById<MaterialButton>(R.id.btnKeyConfirm)?.setOnClickListener {
            // etPin толы болса etConfirmPin-ге фокус ауыстыр, екеуі де толы болса submit
            val pin = etPin.text?.toString() ?: ""
            val confirm = etConfirmPin.text?.toString() ?: ""
            when {
                pin.length >= 4 && confirm.isEmpty() -> etConfirmPin.requestFocus()
                pin.length >= 4 && confirm.isNotEmpty() -> attemptSetup()
                else -> { /* PIN тым қысқа — validate кейін */ }
            }
        }
    }

    private fun attemptSetup() {
        val pin = etPin.text?.toString() ?: ""
        val confirmPin = etConfirmPin.text?.toString() ?: ""
        tilPin.error = null; tilConfirmPin.error = null

        if (pin.length < 4) { tilPin.error = getString(R.string.setup_pin_too_short); return }
        if (pin != confirmPin) { tilConfirmPin.error = getString(R.string.setup_pin_mismatch); return }

        setLoading(true)
        lifecycleScope.launch {
            try {
                val app = application as FinanceApp
                val dbKey = withContext(Dispatchers.Default) {
                    app.cryptoManager.setupNewUser(pin.toCharArray())
                }
                withContext(Dispatchers.IO) { app.openDatabase(dbKey) }
                SessionManager.unlock(dbKey)
                dbKey.fill(0)

                // openDatabase() кейін repository null болмайды
                val repo = app.repository!!
                withContext(Dispatchers.IO) {
                    if (repo.getCategoryCount() == 0) {
                        repo.insertDefaultCategories(DefaultCategories.create(this@SetupActivity))
                    }
                }
                startActivity(Intent(this@SetupActivity, MainActivity::class.java))
                finish()
            } catch (e: Exception) {
                setLoading(false)
                Snackbar.make(btnSetup, "Қате: ${e.javaClass.simpleName}: ${e.message}", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setLoading(on: Boolean) {
        progressBar.visibility = if (on) View.VISIBLE else View.GONE
        btnSetup.isEnabled = !on; etPin.isEnabled = !on; etConfirmPin.isEnabled = !on
    }
}
