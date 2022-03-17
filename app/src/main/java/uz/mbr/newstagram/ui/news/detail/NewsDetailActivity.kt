package uz.mbr.newstagram.ui.news.detail

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import uz.mbr.newstagram.R
import uz.mbr.newstagram.data.model.news.ArticleResponse
import uz.mbr.newstagram.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityNewsDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val articleResponse: ArticleResponse =
            intent.getParcelableExtra<ArticleResponse>("article") as ArticleResponse

        with(binding) {
            backIv.setOnClickListener { finish() }
            bookmarkIv.setOnClickListener {  }

            titleTv.text = articleResponse.title
            newsImageSdv.setImageURI(articleResponse.imageUrl)
            authorTv.text = articleResponse.author
            sourceTv.text = articleResponse.source?.name
            contentTv.text = articleResponse.content
            dateTv.text = articleResponse.publishedAt
        }
        setFullScreenFlags()
//        updateStatusBarToDark()
    }

    override fun onDestroy() {
        clearFullScreenFlags()
//        updateStatusBarToLight()
        super.onDestroy()
    }

    fun setFullScreenFlags() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    fun clearFullScreenFlags() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

//    fun updateStatusBarToDark(@ColorRes statusBarColor: Int = R.color.color_primary_dark) {
//        val window: Window = window
//        val decorView = window.decorView
//
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.statusBarColor = resources.getColor(statusBarColor)
//
//        WindowInsetsControllerCompat(window, decorView).isAppearanceLightStatusBars = false
//    }
//
//    fun updateStatusBarToLight(@ColorRes statusBarColor: Int = R.color.white) {
//        val window: Window = window
//        val decorView = window.decorView
//
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.statusBarColor = resources.getColor(statusBarColor)
//
//        WindowInsetsControllerCompat(window, decorView).isAppearanceLightStatusBars = true
//    }
}