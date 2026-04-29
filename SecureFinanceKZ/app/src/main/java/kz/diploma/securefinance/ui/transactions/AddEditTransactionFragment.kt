package kz.diploma.securefinance.ui.transactions

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.R
import kz.diploma.securefinance.data.FinanceRepository
import kz.diploma.securefinance.data.entity.Account
import kz.diploma.securefinance.data.entity.Category
import kz.diploma.securefinance.data.entity.Transaction
import kz.diploma.securefinance.util.formatMoney
import kz.diploma.securefinance.util.toFormattedDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class AddEditTransactionFragment : Fragment() {

    private lateinit var toggleType: MaterialButtonToggleGroup
    private lateinit var etAmount: TextInputEditText
    private lateinit var spinnerAccount: Spinner
    private lateinit var spinnerCategory: Spinner
    private lateinit var tvDate: TextView
    private lateinit var etNote: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button

    private var selectedDate = System.currentTimeMillis()
    private var accounts = listOf<Account>()
    private var categories = listOf<Category>()
    private var currentType = Transaction.TYPE_EXPENSE
    private var editingTransaction: Transaction? = null

    // TextWatcher — рекурсияны болдырмау үшін флаг
    private var isUpdating = false
    private val amountWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (isUpdating) return
            isUpdating = true
            val raw = s.toString().replace(" ", "").replace(",", ".")
            if (raw.isEmpty()) { isUpdating = false; return }
            val parts = raw.split(".")
            val intPart = parts[0].trimStart('0').ifEmpty { "0" }
            val formatted = intPart.reversed().chunked(3).joinToString(" ").reversed() +
                if (parts.size > 1) ".${parts[1].take(2)}" else ""
            s?.replace(0, s.length, formatted)
            isUpdating = false
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_add_edit_transaction, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        val repo = app.repository ?: run { requireActivity().finish(); return }

        toggleType = view.findViewById(R.id.toggleType)
        etAmount = view.findViewById(R.id.etAmount)
        spinnerAccount = view.findViewById(R.id.spinnerAccount)
        spinnerCategory = view.findViewById(R.id.spinnerCategory)
        tvDate = view.findViewById(R.id.tvDate)
        etNote = view.findViewById(R.id.etNote)
        btnSave = view.findViewById(R.id.btnSave)
        btnDelete = view.findViewById(R.id.btnDelete)

        etAmount.addTextChangedListener(amountWatcher)

        tvDate.text = selectedDate.toFormattedDate()
        tvDate.setOnClickListener { showDatePicker() }

        toggleType.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                currentType = if (checkedId == R.id.btnIncome) Transaction.TYPE_INCOME else Transaction.TYPE_EXPENSE
                loadCategories(repo)
            }
        }
        toggleType.check(R.id.btnExpense)

        btnSave.setOnClickListener { saveTransaction(repo) }
        btnDelete.setOnClickListener { confirmDelete(repo) }

        val txId = arguments?.getLong("transactionId", -1L) ?: -1L
        loadAccounts(repo)
        if (txId > 0) { btnDelete.visibility = View.VISIBLE; loadExistingTransaction(repo, txId) }
        else btnDelete.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        etAmount.removeTextChangedListener(amountWatcher)
    }

    private fun loadAccounts(repo: FinanceRepository) {
        lifecycleScope.launch {
            accounts = withContext(Dispatchers.IO) { repo.getAllAccounts() }
            if (accounts.isEmpty()) {
                Snackbar.make(requireView(), getString(R.string.error_no_accounts), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.account_add)) {
                        findNavController().navigate(R.id.accountEditFragment)
                    }.show()
                findNavController().popBackStack()
                return@launch
            }
            spinnerAccount.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,
                accounts.map { it.name }).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
            loadCategories(repo)
        }
    }

    private fun loadCategories(repo: FinanceRepository) {
        lifecycleScope.launch {
            categories = withContext(Dispatchers.IO) { repo.getCategoriesByType(currentType) }
            spinnerCategory.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,
                categories.map { it.name }).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        }
    }

    private fun loadExistingTransaction(repo: FinanceRepository, id: Long) {
        lifecycleScope.launch {
            val tx = withContext(Dispatchers.IO) { repo.getTransactionById(id) }
            tx?.let {
                editingTransaction = it
                currentType = it.type
                toggleType.check(if (it.type == Transaction.TYPE_INCOME) R.id.btnIncome else R.id.btnExpense)
                // Форматтауды болдырмай тікелей мән қою
                isUpdating = true
                etAmount.setText(it.amount.toBigDecimal().stripTrailingZeros().toPlainString())
                isUpdating = false
                selectedDate = it.date; tvDate.text = it.date.toFormattedDate()
                etNote.setText(it.note)
                loadCategories(repo)
            }
        }
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance().apply { timeInMillis = selectedDate }
        DatePickerDialog(requireContext(), { _, y, m, d ->
            cal.set(y, m, d); selectedDate = cal.timeInMillis; tvDate.text = selectedDate.toFormattedDate()
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun saveTransaction(repo: FinanceRepository) {
        val amountStr = etAmount.text?.toString()?.replace(" ", "") ?: ""
        val amount = amountStr.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            Snackbar.make(requireView(), getString(R.string.required_field), Snackbar.LENGTH_SHORT).show(); return
        }
        if (accounts.isEmpty()) {
            Snackbar.make(requireView(), getString(R.string.account_empty), Snackbar.LENGTH_SHORT).show(); return
        }
        val account = accounts[spinnerAccount.selectedItemPosition.coerceAtMost(accounts.size - 1)]
        val category = categories.getOrNull(spinnerCategory.selectedItemPosition)
        val note = etNote.text?.toString() ?: ""
        val tx = Transaction(
            id = editingTransaction?.id ?: 0,
            amount = amount,
            type = currentType,
            accountId = account.id,
            categoryId = category?.id,
            note = note,
            date = selectedDate
        )

        // EXPENSE болса баланс жеткілікті ме тексеру
        if (currentType == Transaction.TYPE_EXPENSE) {
            val effectiveBalance = if (editingTransaction?.type == Transaction.TYPE_EXPENSE && editingTransaction?.accountId == account.id)
                account.balance + (editingTransaction?.amount ?: 0.0)
            else account.balance
            if (effectiveBalance < amount) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.warning_negative_balance_title))
                    .setMessage(getString(R.string.warning_negative_balance_msg, account.balance.formatMoney()))
                    .setPositiveButton(getString(R.string.warning_proceed)) { _, _ ->
                        lifecycleScope.launch { performSave(repo, tx) }
                    }
                    .setNegativeButton(getString(R.string.cancel), null)
                    .show()
                return
            }
        }

        lifecycleScope.launch { performSave(repo, tx) }
    }

    private suspend fun performSave(repo: FinanceRepository, tx: Transaction) {
        withContext(Dispatchers.IO) {
            val old = editingTransaction
            if (old != null) {
                repo.updateTransaction(old, tx)
            } else {
                repo.addTransaction(tx)
            }
        }
        findNavController().popBackStack()
    }

    private fun confirmDelete(repo: FinanceRepository) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(getString(R.string.transaction_delete_confirm))
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                editingTransaction?.let { tx ->
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) { repo.deleteTransaction(tx) }
                        findNavController().popBackStack()
                    }
                }
            }
            .setNegativeButton(getString(R.string.cancel), null).show()
    }
}
