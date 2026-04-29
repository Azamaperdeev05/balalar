package kz.diploma.securefinance.ui.settings

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.R
import kz.diploma.securefinance.data.entity.Account
import kz.diploma.securefinance.data.entity.Category
import kz.diploma.securefinance.data.entity.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class BackupData(
    val version: Int = 1,
    val accounts: List<Account>,
    val categories: List<Category>,
    val transactions: List<Transaction>
)

class BackupFragment : Fragment() {

    private lateinit var etExportPassword: TextInputEditText
    private lateinit var etImportPassword: TextInputEditText
    private lateinit var btnExport: Button
    private lateinit var btnImport: Button
    private lateinit var btnCsvExport: Button
    private lateinit var progressBar: ProgressBar

    private val gson = Gson()
    private var pendingImportUri: Uri? = null

    private val createFileLauncher = registerForActivityResult(ActivityResultContracts.CreateDocument("application/octet-stream")) { uri ->
        uri?.let { doExport(it) }
    }

    private val openFileLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let { pendingImportUri = it; showImportDialog() }
    }

    private val createCsvLauncher = registerForActivityResult(ActivityResultContracts.CreateDocument("text/csv")) { uri ->
        uri?.let { doCsvExport(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_backup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app0 = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        if (app0.repository == null) { requireActivity().finish(); return }

        etExportPassword = view.findViewById(R.id.etExportPassword)
        etImportPassword = view.findViewById(R.id.etImportPassword)
        btnExport = view.findViewById(R.id.btnExport)
        btnImport = view.findViewById(R.id.btnImport)
        btnCsvExport = view.findViewById(R.id.btnCsvExport)
        progressBar = view.findViewById(R.id.progressBar)

        btnExport.setOnClickListener {
            val password = etExportPassword.text?.toString() ?: ""
            if (password.length < 4) {
                Snackbar.make(requireView(), getString(R.string.setup_pin_too_short), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            createFileLauncher.launch("backup_securefinance.sfkz")
        }

        btnImport.setOnClickListener {
            openFileLauncher.launch(arrayOf("application/octet-stream", "*/*"))
        }

        btnCsvExport.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(getString(R.string.backup_csv_warning))
                .setPositiveButton(getString(R.string.ok)) { _, _ ->
                    createCsvLauncher.launch("transactions.csv")
                }
                .setNegativeButton(getString(R.string.cancel), null)
                .show()
        }
    }

    private fun doExport(uri: Uri) {
        val password = etExportPassword.text?.toString()?.toCharArray() ?: return
        val app = requireActivity().application as FinanceApp
        setLoading(true)

        lifecycleScope.launch {
            try {
                val json = withContext(Dispatchers.IO) {
                    val accounts = app.repository!!.getAllAccounts()
                    val categories = app.repository!!.getAllCategories()
                    val transactions = app.repository!!.getAllTransactions()
                    gson.toJson(BackupData(accounts = accounts, categories = categories, transactions = transactions))
                }
                val encrypted = withContext(Dispatchers.Default) {
                    app.cryptoManager.encryptBackup(json.toByteArray(), password)
                }
                password.fill('0')

                withContext(Dispatchers.IO) {
                    requireContext().contentResolver.openOutputStream(uri)?.use { it.write(encrypted) }
                }
                Snackbar.make(requireView(), getString(R.string.backup_export_success), Snackbar.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Snackbar.make(requireView(), getString(R.string.error) + ": " + e.message, Snackbar.LENGTH_LONG).show()
            } finally {
                setLoading(false)
            }
        }
    }

    private fun showImportDialog() {
        val options = arrayOf(getString(R.string.backup_import_mode_replace), getString(R.string.backup_import_mode_merge))
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.backup_import_mode_title))
            .setItems(options) { _, which ->
                doImport(replace = which == 0)
            }
            .show()
    }

    private fun doImport(replace: Boolean) {
        val uri = pendingImportUri ?: return
        val password = etImportPassword.text?.toString()?.toCharArray() ?: return
        val app = requireActivity().application as FinanceApp
        setLoading(true)

        lifecycleScope.launch {
            try {
                val encrypted = withContext(Dispatchers.IO) {
                    requireContext().contentResolver.openInputStream(uri)?.readBytes()
                } ?: throw Exception("File read error")

                val json = withContext(Dispatchers.Default) {
                    app.cryptoManager.decryptBackup(encrypted, password)
                        ?.let { String(it) }
                        ?: throw Exception("Decryption failed — wrong password?")
                }
                password.fill('0')

                withContext(Dispatchers.IO) {
                    val data = gson.fromJson(json, BackupData::class.java)
                    if (replace) {
                        app.repository!!.clearAndRestore(data.accounts, data.categories, data.transactions)
                    } else {
                        app.repository!!.insertDefaultCategories(data.categories)
                        data.accounts.forEach { app.repository!!.addAccount(it.copy(id = 0, balance = 0.0)) }
                        data.transactions.forEach { app.repository!!.addTransaction(it.copy(id = 0)) }
                    }
                }
                Snackbar.make(requireView(), getString(R.string.backup_import_success), Snackbar.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Snackbar.make(requireView(), getString(R.string.backup_import_error) + ": " + e.message, Snackbar.LENGTH_LONG).show()
            } finally {
                setLoading(false)
            }
        }
    }

    private fun doCsvExport(uri: Uri) {
        val app = requireActivity().application as FinanceApp
        setLoading(true)
        lifecycleScope.launch {
            try {
                val transactions = withContext(Dispatchers.IO) { app.repository!!.getAllTransactions() }
                val csv = buildString {
                    appendLine("id,type,amount,accountId,categoryId,note,date")
                    transactions.forEach { tx ->
                        appendLine("${tx.id},${tx.type},${tx.amount},${tx.accountId},${tx.categoryId ?: ""},\"${tx.note}\",${tx.date}")
                    }
                }
                withContext(Dispatchers.IO) {
                    requireContext().contentResolver.openOutputStream(uri)?.use { it.write(csv.toByteArray()) }
                }
                Snackbar.make(requireView(), getString(R.string.backup_export_success), Snackbar.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Snackbar.make(requireView(), getString(R.string.error) + ": " + e.message, Snackbar.LENGTH_LONG).show()
            } finally {
                setLoading(false)
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        btnExport.isEnabled = !loading
        btnImport.isEnabled = !loading
        btnCsvExport.isEnabled = !loading
    }
}
