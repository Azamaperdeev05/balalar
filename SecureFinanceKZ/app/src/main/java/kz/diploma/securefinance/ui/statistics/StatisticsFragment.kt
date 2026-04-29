package kz.diploma.securefinance.ui.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.google.android.material.chip.ChipGroup
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.R
import kz.diploma.securefinance.data.entity.Transaction
import kz.diploma.securefinance.util.*

class StatisticsFragment : Fragment() {

    private lateinit var viewModel: StatisticsViewModel
    private lateinit var barChart: BarChart
    private lateinit var pieChart: PieChart
    private lateinit var chipGroup: ChipGroup
    private lateinit var tvNoData: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        barChart = view.findViewById(R.id.barChart)
        pieChart = view.findViewById(R.id.pieChart)
        chipGroup = view.findViewById(R.id.chipGroupPeriod)
        tvNoData = view.findViewById(R.id.tvNoData)

        val app = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        val repo = app.repository ?: run { requireActivity().finish(); return }
        viewModel = ViewModelProvider(this, StatisticsViewModelFactory(repo))
            .get(StatisticsViewModel::class.java)

        chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            val period = when (checkedIds.firstOrNull()) {
                R.id.chipWeek -> StatsPeriod.WEEK
                R.id.chipMonth -> StatsPeriod.MONTH
                R.id.chipYear -> StatsPeriod.YEAR
                else -> StatsPeriod.MONTH
            }
            viewModel.setPeriod(period)
        }

        viewModel.income.observe(viewLifecycleOwner) { updateBarChart() }
        viewModel.expense.observe(viewLifecycleOwner) { updateBarChart() }
        viewModel.categoryStats.observe(viewLifecycleOwner) { stats ->
            if (stats.isEmpty()) {
                pieChart.visibility = View.GONE
                tvNoData.visibility = View.VISIBLE
            } else {
                pieChart.visibility = View.VISIBLE
                tvNoData.visibility = View.GONE
                updatePieChart(stats)
            }
        }
    }

    private fun updateBarChart() {
        val income = viewModel.income.value ?: 0.0
        val expense = viewModel.expense.value ?: 0.0

        val incomeEntry = BarEntry(0f, income.toFloat())
        val expenseEntry = BarEntry(1f, expense.toFloat())

        val incomeSet = BarDataSet(listOf(incomeEntry), getString(R.string.statistics_income_label)).apply {
            color = Color.parseColor("#2E7D32")
        }
        val expenseSet = BarDataSet(listOf(expenseEntry), getString(R.string.statistics_expense_label)).apply {
            color = Color.parseColor("#C62828")
        }

        barChart.apply {
            data = BarData(incomeSet, expenseSet)
            description.isEnabled = false
            legend.isEnabled = true
            animateY(500)
            invalidate()
        }
    }

    private fun updatePieChart(stats: List<kz.diploma.securefinance.data.dao.CategoryStat>) {
        val entries = stats.take(8).map { PieEntry(it.total.toFloat(), it.categoryName ?: "?") }
        val colors = stats.take(8).map { it.color ?: Color.GRAY }

        val dataSet = PieDataSet(entries, "").apply {
            this.colors = colors
            valueTextColor = Color.WHITE
            valueTextSize = 10f
        }

        pieChart.apply {
            data = PieData(dataSet)
            description.isEnabled = false
            isDrawHoleEnabled = true
            holeRadius = 35f
            setDrawEntryLabels(false)
            legend.isEnabled = true
            animateY(600)
            invalidate()
        }
    }
}
