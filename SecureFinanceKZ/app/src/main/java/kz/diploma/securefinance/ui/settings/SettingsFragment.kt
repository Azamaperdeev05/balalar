package kz.diploma.securefinance.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import kz.diploma.securefinance.BuildConfig
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.R
import kz.diploma.securefinance.data.entity.ExchangeRate
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.ui.auth.AuthActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsFragment : Fragment() {

    private lateinit var switchBiometric: SwitchMaterial
    private lateinit var tvAutoLock: TextView
    private lateinit var tvAppVersion: TextView
    private lateinit var etRateUsd: TextInputEditText
    private lateinit var etRateEur: TextInputEditText
    private lateinit var etRateRub: TextInputEditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchBiometric = view.findViewById(R.id.switchBiometric)
        tvAutoLock = view.findViewById(R.id.tvAutoLockValue)
        tvAppVersion = view.findViewById(R.id.tvAppVersion)
        etRateUsd = view.findViewById(R.id.etRateUsd)
        etRateEur = view.findViewById(R.id.etRateEur)
        etRateRub = view.findViewById(R.id.etRateRub)

        tvAppVersion.text = BuildConfig.VERSION_NAME

        val app = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        val repo = app.repository ?: run { requireActivity().finish(); return }

        // Биометрия switch
        val bioAvailable = app.biometricHelper.isBiometricAvailable(requireContext())
        switchBiometric.isEnabled = bioAvailable
        switchBiometric.isChecked = app.biometricHelper.isBiometricEnabled

        switchBiometric.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) enableBiometric() else disableBiometric()
        }

        // PIN ауыстыру
        view.findViewById<View>(R.id.rowChangePin).setOnClickListener {
            findNavController().navigate(R.id.changePinFragment)
        }

        // Автоқұлыптау
        view.findViewById<View>(R.id.rowAutoLock).setOnClickListener {
            showAutoLockDialog()
        }

        // Backup
        view.findViewById<View>(R.id.rowBackup).setOnClickListener {
            findNavController().navigate(R.id.backupFragment)
        }

        // Factory Reset
        view.findViewById<View>(R.id.rowFactoryReset).setOnClickListener {
            confirmFactoryReset()
        }

        // Ақпарат
        view.findViewById<View>(R.id.rowAbout).setOnClickListener {
            findNavController().navigate(R.id.aboutFragment)
        }

        updateAutoLockLabel()

        // Айырбас курстарын жүктеу
        lifecycleScope.launch {
            val rates = withContext(Dispatchers.IO) { repo.getExchangeRates() }
                .associateBy { it.currency }
            etRateUsd.setText(rates["USD"]?.rateToKzt?.toBigDecimal()?.stripTrailingZeros()?.toPlainString() ?: "460")
            etRateEur.setText(rates["EUR"]?.rateToKzt?.toBigDecimal()?.stripTrailingZeros()?.toPlainString() ?: "500")
            etRateRub.setText(rates["RUB"]?.rateToKzt?.toBigDecimal()?.stripTrailingZeros()?.toPlainString() ?: "5")
        }

        view.findViewById<View>(R.id.btnSaveRates).setOnClickListener {
            saveExchangeRates(repo)
        }
    }

    private fun saveExchangeRates(repo: kz.diploma.securefinance.data.FinanceRepository) {
        val usd = etRateUsd.text?.toString()?.toDoubleOrNull() ?: return
        val eur = etRateEur.text?.toString()?.toDoubleOrNull() ?: return
        val rub = etRateRub.text?.toString()?.toDoubleOrNull() ?: return
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                repo.upsertExchangeRate(ExchangeRate("USD", usd))
                repo.upsertExchangeRate(ExchangeRate("EUR", eur))
                repo.upsertExchangeRate(ExchangeRate("RUB", rub))
            }
            Snackbar.make(requireView(), getString(R.string.settings_rates_saved), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun enableBiometric() {
        val app = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        val repo = app.repository ?: run { requireActivity().finish(); return }
        val dbKey = SessionManager.getDbKey() ?: return

        try {
            app.biometricHelper.createBiometricKey()
            val cipher = app.biometricHelper.getEncryptCipher()

            app.biometricHelper.authenticate(
                activity = requireActivity() as androidx.fragment.app.FragmentActivity,
                cipher = cipher,
                title = getString(R.string.auth_biometric_title),
                subtitle = getString(R.string.settings_biometric),
                negativeText = getString(R.string.cancel),
                onSuccess = { encCipher ->
                    app.biometricHelper.encryptAndStoreDbKey(encCipher, dbKey)
                    Snackbar.make(requireView(), getString(R.string.settings_biometric_enabled), Snackbar.LENGTH_SHORT).show()
                },
                onError = {
                    switchBiometric.isChecked = false
                    app.biometricHelper.disableBiometric()
                }
            )
        } catch (e: Exception) {
            switchBiometric.isChecked = false
            Snackbar.make(requireView(), e.message ?: getString(R.string.error), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun disableBiometric() {
        val app = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        val repo = app.repository ?: run { requireActivity().finish(); return }
        app.biometricHelper.disableBiometric()
        Snackbar.make(requireView(), getString(R.string.settings_biometric_disabled), Snackbar.LENGTH_SHORT).show()
    }

    private fun showAutoLockDialog() {
        val options = arrayOf(
            getString(R.string.settings_auto_lock_30s),
            getString(R.string.settings_auto_lock_1m),
            getString(R.string.settings_auto_lock_3m),
            getString(R.string.settings_auto_lock_5m),
            getString(R.string.settings_auto_lock_never)
        )
        val timeouts = longArrayOf(30_000, 60_000, 180_000, 300_000, -1)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.settings_auto_lock))
            .setItems(options) { _, which ->
                SessionManager.autoLockTimeoutMs = timeouts[which]
                tvAutoLock.text = options[which]
            }
            .show()
    }

    private fun updateAutoLockLabel() {
        tvAutoLock.text = when (SessionManager.autoLockTimeoutMs) {
            30_000L -> getString(R.string.settings_auto_lock_30s)
            60_000L -> getString(R.string.settings_auto_lock_1m)
            180_000L -> getString(R.string.settings_auto_lock_3m)
            300_000L -> getString(R.string.settings_auto_lock_5m)
            else -> getString(R.string.settings_auto_lock_1m)
        }
    }

    private fun confirmFactoryReset() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.settings_factory_reset))
            .setMessage(getString(R.string.settings_factory_reset_confirm))
            .setPositiveButton(getString(R.string.delete)) { _, _ -> doFactoryReset() }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    private fun doFactoryReset() {
        val app = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        val repo = app.repository ?: run { requireActivity().finish(); return }
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                app.cryptoManager.clearAll()
                app.biometricHelper.disableBiometric()
            }
            SessionManager.lock()
            app.closeDatabase()
            startActivity(Intent(requireContext(), AuthActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
            requireActivity().finish()
        }
    }
}
