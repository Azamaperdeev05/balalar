package kz.diploma.securefinance.ui.accounts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.diploma.securefinance.R
import kz.diploma.securefinance.data.entity.Account
import kz.diploma.securefinance.util.formatMoney

class AccountAdapter(
    private val onItemClick: (Account) -> Unit
) : ListAdapter<Account, AccountAdapter.ViewHolder>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Account>() {
            override fun areItemsTheSame(a: Account, b: Account) = a.id == b.id
            override fun areContentsTheSame(a: Account, b: Account) = a == b
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvAccountName)
        val tvBalance: TextView = view.findViewById(R.id.tvAccountBalance)
        val tvType: TextView = view.findViewById(R.id.tvAccountType)

        fun bind(acc: Account) {
            tvName.text = acc.name
            tvBalance.text = acc.balance.formatMoney(acc.currency)
            tvType.text = acc.type
            itemView.setOnClickListener { onItemClick(acc) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
