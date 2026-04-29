package kz.diploma.securefinance.ui.transactions

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kz.diploma.securefinance.data.FinanceRepository
import kz.diploma.securefinance.data.entity.Transaction
import kz.diploma.securefinance.util.UiState

data class TransactionFilter(
    val fromDate: Long? = null,
    val toDate: Long? = null,
    val type: String? = null,
    val accountId: Long? = null,
    val categoryId: Long? = null,
    val query: String? = null
)

class TransactionsViewModel(private val repository: FinanceRepository) : ViewModel() {

    private val _loadError = MutableLiveData<String?>()
    val loadError: LiveData<String?> = _loadError

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _loadError.postValue(throwable.message ?: "Белгісіз қате")
    }

    private val _filter = MutableLiveData(TransactionFilter())

    val transactions: LiveData<List<Transaction>> = _filter.switchMap { f ->
        repository.getFilteredTransactionsLive(
            fromDate = f.fromDate,
            toDate = f.toDate,
            type = f.type,
            accountId = f.accountId,
            categoryId = f.categoryId,
            query = f.query?.takeIf { it.isNotBlank() }
        )
    }

    fun applyFilter(filter: TransactionFilter) {
        _filter.value = filter
    }

    fun resetFilter() {
        _filter.value = TransactionFilter()
    }

    fun deleteTransaction(tx: Transaction) = viewModelScope.launch(exceptionHandler) {
        repository.deleteTransaction(tx)
    }
}

class TransactionsViewModelFactory(private val repository: FinanceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return TransactionsViewModel(repository) as T
    }
}
