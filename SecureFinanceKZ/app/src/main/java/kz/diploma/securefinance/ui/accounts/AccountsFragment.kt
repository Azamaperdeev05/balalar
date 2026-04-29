package kz.diploma.securefinance.ui.accounts

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.R
import kz.diploma.securefinance.util.formatMoney

class AccountsFragment : Fragment() {

    private lateinit var viewModel: AccountsViewModel
    private lateinit var rvAccounts: RecyclerView
    private lateinit var layoutEmptyState: View
    private lateinit var tvTotal: TextView

    private val adapter = AccountAdapter { account ->
        val bundle = Bundle().apply { putLong("accountId", account.id) }
        findNavController().navigate(R.id.accountEditFragment, bundle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAccounts = view.findViewById(R.id.rvAccounts)
        layoutEmptyState = view.findViewById(R.id.layoutEmptyState)
        tvTotal = view.findViewById(R.id.tvTotal)

        rvAccounts.layoutManager = LinearLayoutManager(requireContext())
        rvAccounts.adapter = adapter

        val app = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        val repo = app.repository ?: run { requireActivity().finish(); return }
        viewModel = ViewModelProvider(this, AccountsViewModelFactory(repo))
            .get(AccountsViewModel::class.java)

        viewModel.accounts.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            val empty = list.isEmpty()
            layoutEmptyState.isVisible = empty
            rvAccounts.isVisible = !empty
        }

        viewModel.totalBalance.observe(viewLifecycleOwner) { total ->
            tvTotal.text = total.formatMoney()
        }

        view.findViewById<FloatingActionButton>(R.id.fabAddAccount)?.setOnClickListener {
            findNavController().navigate(R.id.accountEditFragment)
        }
    }
}
