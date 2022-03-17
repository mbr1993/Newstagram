package uz.mbr.newstagram.ui.news.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mbr.newstagram.data.model.news.ArticleResponse
import uz.mbr.newstagram.databinding.ViewHolderBigNewsBinding
import uz.mbr.newstagram.databinding.ViewHolderSmallNewsBinding

class NewsAdapter(
    private val newsOnClick: (articleResponse: ArticleResponse) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: MutableList<ArticleResponse> = mutableListOf()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SMALL_NEWS -> {
                SmallNewsViewHolder(
                    ViewHolderSmallNewsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_BIG_NEWS -> {
                BigNewsViewHolder(
                    ViewHolderBigNewsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw UnsupportedOperationException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SmallNewsViewHolder -> holder.bindData(items[position])
            is BigNewsViewHolder -> holder.bindData(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_BIG_NEWS else VIEW_TYPE_SMALL_NEWS
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
        private val binding: ViewHolderBigNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ArticleResponse) {
            with(binding) {
                root.setOnClickListener { newsOnClick.invoke(data) }
                newsImageSdv.setImageURI(data.imageUrl)
                titleTv.text = data.title
                dateTv.text = data.publishedAt ?: ""
                sourceTv.text = data.source.name
            }
        }
    }

    inner class SmallNewsViewHolder(
        private val binding: ViewHolderSmallNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ArticleResponse) {
            with(binding) {
                root.setOnClickListener { newsOnClick.invoke(data) }
                newsImageSdv.setImageURI(data.imageUrl)
                titleTv.text = data.title
                dateTv.text = data.publishedAt ?: ""
                sourceTv.text = data.source.name
            }
        }
    }

    private companion object {
        const val VIEW_TYPE_BIG_NEWS = 1
        const val VIEW_TYPE_SMALL_NEWS = 2
    }
}