package kz.diploma.securefinance.ui.categories

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.R
import kz.diploma.securefinance.data.entity.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryEditFragment : Fragment() {

    private lateinit var viewModel: CategoriesViewModel
    private lateinit var etName: TextInputEditText
    private lateinit var rgType: RadioGroup
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button

    private var editingCategory: Category? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_category_edit, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        val repo = app.repository ?: run { requireActivity().finish(); return }

        etName = view.findViewById(R.id.etCategoryName)
        rgType = view.findViewById(R.id.rgCategoryType)
        btnSave = view.findViewById(R.id.btnSaveCategory)
        btnDelete = view.findViewById(R.id.btnDeleteCategory)

        val defaultType = arguments?.getString("categoryType") ?: Category.TYPE_EXPENSE
        rgType.check(if (defaultType == Category.TYPE_INCOME) R.id.rbIncome else R.id.rbExpense)

        viewModel = ViewModelProvider(this, CategoriesViewModelFactory(repo)).get(CategoriesViewModel::class.java)

        val catId = arguments?.getLong("categoryId", -1L) ?: -1L
        if (catId > 0) {
            btnDelete.visibility = View.VISIBLE
            lifecycleScope.launch {
                val all = withContext(Dispatchers.IO) {
                    repo.getCategoriesByType(Category.TYPE_EXPENSE) + repo.getCategoriesByType(Category.TYPE_INCOME)
                }
                all.firstOrNull { it.id == catId }?.let {
                    editingCategory = it
                    etName.setText(it.name)
                    rgType.check(if (it.type == Category.TYPE_INCOME) R.id.rbIncome else R.id.rbExpense)
                }
            }
        } else btnDelete.visibility = View.GONE

        btnSave.setOnClickListener { saveCategory() }
        btnDelete.setOnClickListener { confirmDelete() }
    }

    private fun saveCategory() {
        val name = etName.text?.toString()?.trim() ?: ""
        if (name.isEmpty()) { etName.error = getString(R.string.required_field); return }
        val type = if (rgType.checkedRadioButtonId == R.id.rbIncome) Category.TYPE_INCOME else Category.TYPE_EXPENSE
        val old = editingCategory
        if (old != null) viewModel.updateCategory(old.copy(name = name, type = type))
        else viewModel.addCategory(Category(name = name, type = type, color = Color.parseColor("#4CAF50")))
        findNavController().popBackStack()
    }

    private fun confirmDelete() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(getString(R.string.category_delete_confirm))
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                editingCategory?.let { viewModel.deleteCategory(it) }
                findNavController().popBackStack()
            }
            .setNegativeButton(getString(R.string.cancel), null).show()
    }
}
