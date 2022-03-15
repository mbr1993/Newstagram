package uz.mbr.newstagram.data.source

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.mbr.newstagram.data.model.ArticleListResponse

interface ArticleRestService {
    @GET("$API_TOP_HEADINGS$API_API_KEY")
    fun getArticles(
        @Query(FIELD_COUNTRY) country: String = "us"
    ): Call<ArticleListResponse>

    @GET("$API_TOP_HEADINGS$API_API_KEY")
    fun getPopularArticles(
        @Query(FIELD_COUNTRY) country: String = "ru",
        @Query(FIELD_SORT) sortBy: String = "popularity"
    ): Call<ArticleListResponse>

    @GET("$API_TOP_HEADINGS$API_API_KEY")
    fun getPopularSportArticles(
        @Query(FIELD_CATEGORY) category: String = "sport",
        @Query(FIELD_COUNTRY) country: String = "us",
        @Query(FIELD_SORT) sortBy: String = "popularity"
    ): Call<ArticleListResponse>

    private companion object {
        const val API_TOP_HEADINGS: String = "top-headlines"
        const val API_API_KEY: String = "?country=us&apiKey=f64e5b340d954f85bdec4e8ad49e15fc"

        const val FIELD_COUNTRY: String = "country"
        const val FIELD_CATEGORY: String = "category"
        const val FIELD_SORT: String = "country"
    }
}