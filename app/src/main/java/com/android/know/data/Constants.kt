package com.android.know.data

const val API_KEY_HEADER = "X-Api-Key"
const val BASE_URL = "https://newsapi.org"
const val NEWS = "/v2/everything"
const val TOP_HEADLINES = "/v2/top-headlines"

object QueryParameters {
    const val Q = "q"
    const val FROM = "from"
    const val SORT_BY = "sortBy"
    const val DOMAIN = "domains"
    const val COUNTRY = "country"
    const val CATEGORY = "category"
}

// TODO: Delete
object Default {
    const val DEFAULT_COUNTRY = "bg"
    const val DEFAULT_CATEGORY = "business"
}
