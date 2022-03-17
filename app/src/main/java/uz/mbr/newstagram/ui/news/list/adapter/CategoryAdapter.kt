package uz.mbr.newstagram.ui.news.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mbr.newstagram.data.model.news.SourceResponse
import uz.mbr.newstagram.databinding.ViewHolderCategoryBinding

class CategoryAdapter(
    private val sourceOnClick: (sourceResponse: SourceResponse) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.NewsViewHolder>() {

    var items: MutableList<SourceResponse> = mutableListOf()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ViewHolderCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(newItems: List<SourceResponse>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addData(newItems: List<SourceResponse>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(
        private val binding: ViewHolderCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: SourceResponse, position: Int) {
            with(binding) {
                root.setOnClickListener { sourceOnClick.invoke(data) }
                titleTv.text = data.name
                titleTv.isSelected = position == 0
            }
        }
    }
}