package uz.mbr.newstagram.ui.news.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mbr.newstagram.data.model.news.SourceResponse
import uz.mbr.newstagram.databinding.ViewHolderCategoryBinding

class CategoryAdapterB(private val sourceOnClick: (sourceResponse: SourceResponse) -> Unit) :
    RecyclerView.Adapter<CategoryAdapterB.CategoryViewHolder>() {

    var categories: MutableList<SourceResponse> = mutableListOf()
        private set


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ViewHolderCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindData(categories[position], position)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setData(newItems: List<SourceResponse>) {
        categories.clear()
        categories.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addData(newItems: List<SourceResponse>) {
        categories.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(private val binding: ViewHolderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: SourceResponse, position: Int) {
            with(binding) {
                root.setOnClickListener { sourceOnClick.invoke(data) }
                titleTv.text = data.name
                titleTv.isSelected = position == 0
            }
        }
    }
}