package uz.mbr.newstagram.ui.news.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import kotlinx.android.synthetic.main.activity_news_list.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import uz.mbr.newstagram.BuildConfig.BASE_URL
import uz.mbr.newstagram.data.model.news.ArticleListResponse
import uz.mbr.newstagram.data.model.news.ArticleResponse
import uz.mbr.newstagram.data.model.news.SourceResponse
import uz.mbr.newstagram.data.source.ArticleRestService
import uz.mbr.newstagram.databinding.ActivityNewsListBinding
import uz.mbr.newstagram.ui.news.detail.NewsDetailActivity
import uz.mbr.newstagram.ui.news.list.adapter.CategoryAdapterB
import uz.mbr.newstagram.ui.news.list.adapter.NewsAdapter
import uz.mbr.newstagram.ui.news.pager.NewsPagerActivity

class NewsListActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val articleRestService: ArticleRestService = retrofit.create()
    private var callNewsList: Call<ArticleListResponse>? = null

    private val newsAdapter = NewsAdapter(
        newsOnClick = { openNewsDetail(it) },
        imageOnClick = { openNewsPager(it) }
    )

    private val categoryAdapter = CategoryAdapterB { }

    private val binding by lazy { ActivityNewsListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        loadNews()
    }

    override fun onDestroy() {
        callNewsList?.cancel()
        super.onDestroy()
    }

    private fun initViews() {
        with(binding) {
            newsRv.layoutManager =
                LinearLayoutManager(this@NewsListActivity, VERTICAL, false)
            newsRv.adapter = newsAdapter

            categoriesRv.layoutManager =
                LinearLayoutManager(this@NewsListActivity, HORIZONTAL, false)
            categoriesRv.adapter = categoryAdapter
        }
    }

    private fun loadNews() {
        callNewsList = articleRestService.getPopularArticles()

        callNewsList?.enqueue(object : Callback<ArticleListResponse> {
            override fun onResponse(
                call: Call<ArticleListResponse>,
                response: Response<ArticleListResponse>
            ) {
                Timber.d(response.toString())

                val articles = response.body()?.articles ?: listOf()
                val categories: List<SourceResponse> = articles.mapNotNull { it.source }
                categoryAdapter.setData(categories)
                newsAdapter.setData(articles)
            }

            override fun onFailure(call: Call<ArticleListResponse>, t: Throwable) {
                Timber.e(t)
            }
        })
    }

    private fun openNewsDetail(articleResponse: ArticleResponse) {
        val intent = Intent(this, NewsDetailActivity::class.java)
        intent.putExtra("article", articleResponse)
        startActivity(intent)
    }

    private fun openNewsPager(position: Int) {
        val intent = Intent(this, NewsPagerActivity::class.java)
        val articleArrayList: ArrayList<ArticleResponse> = arrayListOf()
        val articles = newsAdapter.items
        articles.forEach { articleArrayList.add(it) }

        intent.putParcelableArrayListExtra("articles", articleArrayList)
        intent.putExtra("current_position", position)
        startActivity(intent)
    }
}