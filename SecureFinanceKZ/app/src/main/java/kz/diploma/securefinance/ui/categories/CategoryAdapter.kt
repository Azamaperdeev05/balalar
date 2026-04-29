package kz.diploma.securefinance.ui.categories

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.diploma.securefinance.R
import kz.diploma.securefinance.data.entity.Category

class CategoryAdapter(
    private val onItemClick: (Category) -> Unit
) : ListAdapter<Category, CategoryAdapter.ViewHolder>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(a: Category, b: Category) = a.id == b.id
            override fun areContentsTheSame(a: Category, b: Category) = a == b
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvCategoryName)

        fun bind(cat: Category) {
            tvName.text = cat.name
            itemView.setOnClickListener { onItemClick(cat) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
