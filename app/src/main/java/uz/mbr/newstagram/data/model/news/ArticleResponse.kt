package uz.mbr.newstagram.data.model.news

import com.google.gson.annotations.SerializedName
import uz.mbr.newstagram.data.model.news.SourceResponse
import java.io.Serializable

data class ArticleResponse(

    @SerializedName("author")
    val author: String?,

    @SerializedName("content")
    val content: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("source")
    val source: SourceResponse,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("urlToImage")
    val imageUrl: String?
) : Serializable