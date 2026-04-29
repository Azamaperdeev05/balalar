package kz.diploma.securefinance.ui.dashboard

import androidx.lifecycle.*
import kz.diploma.securefinance.data.FinanceRepository
import kz.diploma.securefinance.data.dao.CategoryStat
import kz.diploma.securefinance.data.entity.Account
import kz.diploma.securefinance.data.entity.ExchangeRate
import kz.diploma.securefinance.data.entity.Transaction
import kz.diploma.securefinance.util.monthEnd
import kz.diploma.securefinance.util.monthStart

class DashboardViewModel(private val repository: FinanceRepository) : ViewModel() {

    private val accounts: LiveData<List<Account>> = repository.getAllAccountsLive()
    private val exchangeRates: LiveData<List<ExchangeRate>> = repository.getExchangeRatesLive()

    // KZT-ге конверсияланған жалпы баланс
    val totalBalance: LiveData<Double> = MediatorLiveData<Double>().also { mediator ->
        fun recalculate() {
            val accs = accounts.value ?: return
            val rates = exchangeRates.value?.associateBy { it.currency } ?: emptyMap()
            mediator.value = accs.sumOf { account ->
                if (account.currency == "KZT") account.balance
                else account.balance * (rates[account.currency]?.rateToKzt ?: 1.0)
            }
        }
        mediator.addSource(accounts) { recalculate() }
        mediator.addSource(exchangeRates) { recalculate() }
    }

    val recentTransactions: LiveData<List<Transaction>> = repository.getRecentTransactionsLive(10)

    private val _monthRange = MutableLiveData(Pair(monthStart(), monthEnd()))

    val monthIncome: LiveData<Double> = _monthRange.switchMap { (from, to) ->
        repository.getMonthSumLive(Transaction.TYPE_INCOME, from, to)
    }

    val monthExpense: LiveData<Double> = _monthRange.switchMap { (from, to) ->
        repository.getMonthSumLive(Transaction.TYPE_EXPENSE, from, to)
    }

    val categoryStats: LiveData<List<CategoryStat>> = _monthRange.switchMap { (from, to) ->
        repository.getCategoryStatsLive(from, to)
    }

    fun refreshMonth() {
        _monthRange.value = Pair(monthStart(), monthEnd())
    }
}

class DashboardViewModelFactory(private val repository: FinanceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DashboardViewModel(repository) as T
    }
}
