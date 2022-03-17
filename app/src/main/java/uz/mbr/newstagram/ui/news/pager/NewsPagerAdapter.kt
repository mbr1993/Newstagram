package uz.mbr.newstagram.ui.news.pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mbr.newstagram.data.model.news.ArticleResponse
import uz.mbr.newstagram.databinding.ViewHolderBigNewsBinding
import uz.mbr.newstagram.databinding.ViewHolderPagerNewsBinding

class NewsPagerAdapter(
    private val newsOnClick: (articleResponse: ArticleResponse) -> Unit
) : RecyclerView.Adapter<NewsPagerAdapter.BigNewsViewHolder>() {

    var items: MutableList<ArticleResponse> = mutableListOf()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BigNewsViewHolder {
        return BigNewsViewHolder(
            ViewHolderPagerNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: BigNewsViewHolder, position: Int) {
        holder.bindData(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(newItems: List<ArticleResponse>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addData(newItems: List<ArticleResponse>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class BigNewsViewHolder(
        private val binding: ViewHolderPagerNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ArticleResponse, position: Int) {
            with(binding) {
                root.setOnClickListener { newsOnClick.invoke(data) }
                newsImageSdv.setImageURI(data.imageUrl)
                titleTv.text = data.title
                dateTv.text = data.publishedAt ?: ""
                sourceTv.text = data.source?.name
            }
        }
    }
}