package com.android.know.data.model
import com.google.gson.annotations.SerializedName

data class ArticleModel(
    @SerializedName("source")
    val source: SourceModel?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
)

data class SourceModel(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)

data class NewsData(
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?,
    @SerializedName("articles")
    val articles: List<ArticleModel>?
)
