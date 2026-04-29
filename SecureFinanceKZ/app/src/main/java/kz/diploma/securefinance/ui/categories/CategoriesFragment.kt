package kz.diploma.securefinance.ui.categories

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.R
import kz.diploma.securefinance.data.entity.Category

class CategoriesFragment : Fragment() {

    private lateinit var viewModel: CategoriesViewModel
    private lateinit var rvCategories: RecyclerView
    private lateinit var tabLayout: TabLayout
    private lateinit var layoutEmptyState: View

    private val adapter = CategoryAdapter { cat ->
        val bundle = Bundle().apply { putLong("categoryId", cat.id) }
        findNavController().navigate(R.id.categoryEditFragment, bundle)
    }

    private var currentType = Category.TYPE_EXPENSE

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCategories = view.findViewById(R.id.rvCategories)
        tabLayout = view.findViewById(R.id.tabLayout)
        layoutEmptyState = view.findViewById(R.id.layoutEmptyState)

        rvCategories.layoutManager = LinearLayoutManager(requireContext())
        rvCategories.adapter = adapter

        val app = requireActivity().application as FinanceApp
        if (!SessionManager.isUnlocked) { requireActivity().finish(); return }
        val repo = app.repository ?: run { requireActivity().finish(); return }
        viewModel = ViewModelProvider(this, CategoriesViewModelFactory(repo))
            .get(CategoriesViewModel::class.java)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentType = if (tab?.position == 0) Category.TYPE_EXPENSE else Category.TYPE_INCOME
                observeCategories()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        observeCategories()

        view.findViewById<View>(R.id.fabAddCategory)?.setOnClickListener {
            val bundle = Bundle().apply { putString("categoryType", currentType) }
            findNavController().navigate(R.id.categoryEditFragment, bundle)
        }
    }

    private fun observeCategories() {
        viewModel.getCategoriesByType(currentType).observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            val empty = list.isEmpty()
            layoutEmptyState.isVisible = empty
            rvCategories.isVisible = !empty
        }
    }
}
