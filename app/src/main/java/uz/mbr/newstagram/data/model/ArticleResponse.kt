package uz.mbr.newstagram.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleResponse(

    @SerializedName("author")
    val author: String,

    @SerializedName("content")
    val content: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("source")
    val source: SourceResponse,

    @SerializedName("title")
    val title: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("imageUrl")
    val imageUrl: String?

) : Serializable
