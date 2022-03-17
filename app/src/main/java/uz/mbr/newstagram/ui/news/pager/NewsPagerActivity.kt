package uz.mbr.newstagram.ui.news.pager

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.recyclerview.widget.PagerSnapHelper
import uz.mbr.newstagram.data.model.news.ArticleResponse
import uz.mbr.newstagram.data.model.news.SourceResponse
import uz.mbr.newstagram.databinding.ActivityNewsDetailBinding
import uz.mbr.newstagram.databinding.ActivityNewsPagerBinding
import uz.mbr.newstagram.ui.news.detail.NewsDetailActivity
import java.util.ArrayList

class NewsPagerActivity : AppCompatActivity() {

    private val binding by lazy { ActivityNewsPagerBinding.inflate(layoutInflater) }

    private val newsPagerAdapter = NewsPagerAdapter{openNewsDetail(it)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFullScreenFlags()

        val articles: ArrayList<ArticleResponse> =
            intent.getParcelableArrayListExtra<ArticleResponse>("articles") as ArrayList<ArticleResponse>

        val currentPosition: Int = intent.getIntExtra("current_position", 0)


        with(binding){
            backIv.setOnClickListener { finish() }
            bookmarkIv.setOnClickListener {  }
            newsRv.layoutManager = LinearLayoutManager(this@NewsPagerActivity, HORIZONTAL, false)
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(newsRv)
            newsRv.adapter = newsPagerAdapter
            newsPagerAdapter.setData(articles)
            newsRv.scrollToPosition(currentPosition)
        }
    }

    override fun onDestroy() {
        clearFullScreenFlags()
        super.onDestroy()
    }

    private fun openNewsDetail(articleResponse: ArticleResponse) {
        val intent = Intent(this, NewsDetailActivity::class.java)
        intent.putExtra("article", articleResponse)
        startActivity(intent)
    }

    private fun setFullScreenFlags() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    private fun clearFullScreenFlags() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }
}