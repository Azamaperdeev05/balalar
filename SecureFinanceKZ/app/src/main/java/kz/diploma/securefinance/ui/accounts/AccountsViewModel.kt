package kz.diploma.securefinance.ui.accounts

import androidx.lifecycle.*
import kz.diploma.securefinance.data.FinanceRepository
import kz.diploma.securefinance.data.entity.Account
import kotlinx.coroutines.launch

class AccountsViewModel(private val repository: FinanceRepository) : ViewModel() {

    val accounts: LiveData<List<Account>> = repository.getAllAccountsLive()
    val totalBalance: LiveData<Double> = repository.getTotalBalanceLive()

    fun deleteAccount(account: Account) = viewModelScope.launch {
        repository.deleteAccount(account)
    }

    fun addAccount(account: Account) = viewModelScope.launch {
        repository.addAccount(account)
    }

    fun updateAccount(account: Account) = viewModelScope.launch {
        repository.updateAccount(account)
    }
}

class AccountsViewModelFactory(private val repository: FinanceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return AccountsViewModel(repository) as T
    }
}
