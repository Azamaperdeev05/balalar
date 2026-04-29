package kz.diploma.securefinance.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.R
import kz.diploma.securefinance.ui.transactions.TransactionAdapter
import kz.diploma.securefinance.ui.transactions.TransactionItem
import kz.diploma.securefinance.util.formatMoney

class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var tvTotalBalance: TextView
    private lateinit var tvIncome: TextView
    private lateinit var tvExpense: TextView
    private lateinit var tvDiff: TextView
    private lateinit var rvTransactions: RecyclerView
    private lateinit var pieChart: PieChart
    private lateinit var tvNoTransactions: TextView
    private val adapter = TransactionAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_dashboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        val repo = app.repository ?: run { requireActivity().finish(); return }

        tvTotalBalance = view.findViewById(R.id.tvTotalBalance)
        tvIncome = view.findViewById(R.id.tvIncome)
        tvExpense = view.findViewById(R.id.tvExpense)
        tvDiff = view.findViewById(R.id.tvDiff)
        rvTransactions = view.findViewById(R.id.rvRecentTransactions)
        pieChart = view.findViewById(R.id.pieChart)
        tvNoTransactions = view.findViewById(R.id.tvNoTransactions)

        rvTransactions.layoutManager = LinearLayoutManager(requireContext())
        rvTransactions.adapter = adapter

        viewModel = ViewModelProvider(this, DashboardViewModelFactory(repo))
            .get(DashboardViewModel::class.java)

        viewModel.totalBalance.observe(viewLifecycleOwner) { tvTotalBalance.text = it.formatMoney() }

        viewModel.monthIncome.observe(viewLifecycleOwner) {
            tvIncome.text = it.formatMoney()
            updateDiff()
        }
        viewModel.monthExpense.observe(viewLifecycleOwner) {
            tvExpense.text = it.formatMoney()
            updateDiff()
        }

        viewModel.recentTransactions.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list.map { TransactionItem.Entry(it) })
            tvNoTransactions.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
            rvTransactions.visibility = if (list.isEmpty()) View.GONE else View.VISIBLE
        }

        viewModel.categoryStats.observe(viewLifecycleOwner) { updatePieChart(it) }
    }

    private fun updateDiff() {
        val diff = (viewModel.monthIncome.value ?: 0.0) - (viewModel.monthExpense.value ?: 0.0)
        tvDiff.text = diff.formatMoney()
        tvDiff.setTextColor(if (diff >= 0) Color.parseColor("#2E7D32") else Color.parseColor("#C62828"))
    }

    private fun updatePieChart(stats: List<kz.diploma.securefinance.data.dao.CategoryStat>) {
        if (stats.isEmpty()) { pieChart.visibility = View.GONE; return }
        pieChart.visibility = View.VISIBLE
        val entries = stats.take(6).map { PieEntry(it.total.toFloat(), it.categoryName ?: "?") }
        val dataSet = PieDataSet(entries, "").apply {
            colors = stats.take(6).map { it.color ?: Color.GRAY }
            valueTextColor = Color.WHITE
            valueTextSize = 11f
        }
        pieChart.apply {
            data = PieData(dataSet)
            description.isEnabled = false
            isDrawHoleEnabled = true; holeRadius = 40f
            setDrawEntryLabels(false)
            animateY(600); invalidate()
        }
    }
}
