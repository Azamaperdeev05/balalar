package kz.diploma.securefinance.ui.transactions

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.R
import kz.diploma.securefinance.util.groupByDateHeaders

class TransactionsFragment : Fragment() {

    private lateinit var viewModel: TransactionsViewModel
    private lateinit var rvTransactions: RecyclerView
    private lateinit var layoutEmptyState: View
    private val adapter = TransactionAdapter { tx ->
        val bundle = Bundle().apply { putLong("transactionId", tx.id) }
        findNavController().navigate(R.id.addEditTransactionFragment, bundle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTransactions = view.findViewById(R.id.rvTransactions)
        layoutEmptyState = view.findViewById(R.id.layoutEmptyState)

        rvTransactions.layoutManager = LinearLayoutManager(requireContext())
        rvTransactions.adapter = adapter

        val app = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        val repo = app.repository ?: run { requireActivity().finish(); return }
        viewModel = ViewModelProvider(this, TransactionsViewModelFactory(repo))
            .get(TransactionsViewModel::class.java)

        viewModel.transactions.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list.groupByDateHeaders(requireContext()))
            val empty = list.isEmpty()
            layoutEmptyState.isVisible = empty
            rvTransactions.isVisible = !empty
        }

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                val current = viewModel.transactions.value?.let { TransactionFilter(query = newText) }
                    ?: TransactionFilter(query = newText)
                viewModel.applyFilter(TransactionFilter(query = newText))
                return true
            }
        })
    }
}
