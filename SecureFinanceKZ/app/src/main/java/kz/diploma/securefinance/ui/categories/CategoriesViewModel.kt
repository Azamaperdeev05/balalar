package kz.diploma.securefinance.ui.categories

import androidx.lifecycle.*
import kz.diploma.securefinance.data.FinanceRepository
import kz.diploma.securefinance.data.entity.Category
import kotlinx.coroutines.launch

class CategoriesViewModel(private val repository: FinanceRepository) : ViewModel() {

    fun getCategoriesByType(type: String): LiveData<List<Category>> =
        repository.getCategoriesByTypeLive(type)

    fun addCategory(category: Category) = viewModelScope.launch {
        repository.addCategory(category)
    }

    fun updateCategory(category: Category) = viewModelScope.launch {
        repository.updateCategory(category)
    }

    fun deleteCategory(category: Category) = viewModelScope.launch {
        repository.deleteCategory(category)
    }
}

class CategoriesViewModelFactory(private val repository: FinanceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return CategoriesViewModel(repository) as T
    }
}
