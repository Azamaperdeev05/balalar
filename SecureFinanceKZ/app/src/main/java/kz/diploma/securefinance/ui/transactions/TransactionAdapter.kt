package kz.diploma.securefinance.ui.transactions

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.diploma.securefinance.R
import kz.diploma.securefinance.data.entity.Transaction
import kz.diploma.securefinance.util.formatMoney
import kz.diploma.securefinance.util.toFormattedDate

sealed class TransactionItem {
    data class Header(val label: String) : TransactionItem()
    data class Entry(val transaction: Transaction) : TransactionItem()
}

class TransactionAdapter(
    private val onItemClick: ((Transaction) -> Unit)? = null
) : ListAdapter<TransactionItem, RecyclerView.ViewHolder>(DIFF) {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ENTRY = 1

        private val DIFF = object : DiffUtil.ItemCallback<TransactionItem>() {
            override fun areItemsTheSame(a: TransactionItem, b: TransactionItem): Boolean {
                return when {
                    a is TransactionItem.Header && b is TransactionItem.Header -> a.label == b.label
                    a is TransactionItem.Entry && b is TransactionItem.Entry -> a.transaction.id == b.transaction.id
                    else -> false
                }
            }
            override fun areContentsTheSame(a: TransactionItem, b: TransactionItem) = a == b
        }
    }

    inner class HeaderViewHolder(val tv: TextView) : RecyclerView.ViewHolder(tv) {
        fun bind(header: TransactionItem.Header) { tv.text = header.label }
    }

    inner class EntryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAmount: TextView = view.findViewById(R.id.tvAmount)
        val tvNote: TextView = view.findViewById(R.id.tvNote)
        val tvDate: TextView = view.findViewById(R.id.tvDate)

        fun bind(entry: TransactionItem.Entry) {
            val tx = entry.transaction
            tvAmount.text = tx.amount.formatMoney()
            tvAmount.setTextColor(
                if (tx.type == Transaction.TYPE_INCOME) Color.parseColor("#2E7D32")
                else Color.parseColor("#C62828")
            )
            tvNote.text = tx.note.ifEmpty { "-" }
            tvDate.text = tx.date.toFormattedDate()
            itemView.setOnClickListener { onItemClick?.invoke(tx) }
        }
    }

    override fun getItemViewType(position: Int) =
        if (getItem(position) is TransactionItem.Header) TYPE_HEADER else TYPE_ENTRY

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_HEADER) {
            HeaderViewHolder(inflater.inflate(R.layout.item_transaction_header, parent, false) as TextView)
        } else {
            EntryViewHolder(inflater.inflate(R.layout.item_transaction, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is TransactionItem.Header -> (holder as HeaderViewHolder).bind(item)
            is TransactionItem.Entry -> (holder as EntryViewHolder).bind(item)
        }
    }
}
