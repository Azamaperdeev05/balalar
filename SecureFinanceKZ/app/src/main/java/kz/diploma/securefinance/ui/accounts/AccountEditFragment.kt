package kz.diploma.securefinance.ui.accounts

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.R
import kz.diploma.securefinance.data.entity.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountEditFragment : Fragment() {

    private lateinit var etName: TextInputEditText
    private lateinit var spinnerType: Spinner
    private lateinit var etBalance: TextInputEditText
    private lateinit var spinnerCurrency: Spinner
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button

    private var editingAccount: Account? = null
    private val types = listOf(Account.TYPE_CASH, Account.TYPE_CARD, Account.TYPE_SAVINGS, Account.TYPE_OTHER)
    private val currencies = listOf("KZT", "USD", "EUR", "RUB")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_account_edit, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        val repo = app.repository ?: run { requireActivity().finish(); return }

        etName = view.findViewById(R.id.etAccountName)
        spinnerType = view.findViewById(R.id.spinnerAccountType)
        etBalance = view.findViewById(R.id.etInitialBalance)
        spinnerCurrency = view.findViewById(R.id.spinnerCurrency)
        btnSave = view.findViewById(R.id.btnSaveAccount)
        btnDelete = view.findViewById(R.id.btnDeleteAccount)

        spinnerType.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,
            listOf(getString(R.string.account_type_cash), getString(R.string.account_type_card),
                getString(R.string.account_type_savings), getString(R.string.account_type_other)))
            .also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        spinnerCurrency.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencies)
            .also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        btnSave.setOnClickListener { saveAccount(repo) }
        btnDelete.setOnClickListener { confirmDelete(repo) }

        val accId = arguments?.getLong("accountId", -1L) ?: -1L
        if (accId > 0) {
            btnDelete.visibility = View.VISIBLE
            lifecycleScope.launch {
                val acc = withContext(Dispatchers.IO) { repo.getAccountById(accId) }
                acc?.let {
                    editingAccount = it
                    etName.setText(it.name)
                    spinnerType.setSelection(types.indexOf(it.type).coerceAtLeast(0))
                    etBalance.setText(it.balance.toString())
                    spinnerCurrency.setSelection(currencies.indexOf(it.currency).coerceAtLeast(0))
                }
            }
        } else btnDelete.visibility = View.GONE
    }

    private fun saveAccount(repo: kz.diploma.securefinance.data.FinanceRepository) {
        val name = etName.text?.toString()?.trim() ?: ""
        if (name.isEmpty()) { etName.error = getString(R.string.required_field); return }
        val balance = etBalance.text?.toString()?.toDoubleOrNull() ?: 0.0
        val type = types[spinnerType.selectedItemPosition]
        val currency = currencies[spinnerCurrency.selectedItemPosition]
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val old = editingAccount
                if (old != null) repo.updateAccount(old.copy(name = name, type = type, currency = currency))
                else repo.addAccount(Account(name = name, type = type, balance = balance,
                    currency = currency, color = android.graphics.Color.parseColor("#4CAF50")))
            }
            findNavController().popBackStack()
        }
    }

    private fun confirmDelete(repo: kz.diploma.securefinance.data.FinanceRepository) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(getString(R.string.account_delete_confirm))
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                editingAccount?.let { acc ->
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) { repo.deleteAccount(acc) }
                        findNavController().popBackStack()
                    }
                }
            }
            .setNegativeButton(getString(R.string.cancel), null).show()
    }
}
