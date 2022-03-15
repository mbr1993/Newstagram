package uz.mbr.newstagram.data.model

import com.google.gson.annotations.SerializedName

data class ArticleListResponse(

    @SerializedName("article")
    val article: List<ArticleResponse>,

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResult")
    val totalResult: Int
)
