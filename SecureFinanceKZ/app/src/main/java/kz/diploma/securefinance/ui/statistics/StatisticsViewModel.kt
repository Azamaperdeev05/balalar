package kz.diploma.securefinance.ui.statistics

import androidx.lifecycle.*
import kz.diploma.securefinance.data.FinanceRepository
import kz.diploma.securefinance.data.dao.CategoryStat
import kz.diploma.securefinance.data.entity.Transaction
import kz.diploma.securefinance.util.dayStart
import kz.diploma.securefinance.util.monthEnd
import kz.diploma.securefinance.util.monthStart
import java.util.Calendar

enum class StatsPeriod { WEEK, MONTH, QUARTER, YEAR }

class StatisticsViewModel(private val repository: FinanceRepository) : ViewModel() {

    private val _period = MutableLiveData(StatsPeriod.MONTH)

    private val _dateRange: LiveData<Pair<Long, Long>> = _period.map { period ->
        val now = System.currentTimeMillis()
        val cal = Calendar.getInstance()
        val from = when (period) {
            StatsPeriod.WEEK -> {
                cal.set(Calendar.DAY_OF_WEEK, cal.firstDayOfWeek)
                dayStart(cal.timeInMillis)
            }
            StatsPeriod.MONTH -> monthStart()
            StatsPeriod.QUARTER -> {
                val month = (cal.get(Calendar.MONTH) / 3) * 3
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, 1)
                dayStart(cal.timeInMillis)
            }
            StatsPeriod.YEAR -> {
                cal.set(Calendar.MONTH, 0)
                cal.set(Calendar.DAY_OF_MONTH, 1)
                dayStart(cal.timeInMillis)
            }
        }
        Pair(from, now)
    }

    val income: LiveData<Double> = _dateRange.switchMap { (from, to) ->
        repository.getMonthSumLive(Transaction.TYPE_INCOME, from, to)
    }

    val expense: LiveData<Double> = _dateRange.switchMap { (from, to) ->
        repository.getMonthSumLive(Transaction.TYPE_EXPENSE, from, to)
    }

    val categoryStats: LiveData<List<CategoryStat>> = _dateRange.switchMap { (from, to) ->
        repository.getCategoryStatsLive(from, to)
    }

    fun setPeriod(period: StatsPeriod) {
        _period.value = period
    }
}

class StatisticsViewModelFactory(private val repository: FinanceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return StatisticsViewModel(repository) as T
    }
}
