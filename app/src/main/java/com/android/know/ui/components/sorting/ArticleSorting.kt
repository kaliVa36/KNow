package com.android.know.ui.components.sorting

import com.android.know.R

enum class ArticleSorting(val value: Int) {
    RELEVANCY(R.string.relevancy),
    POPULARITY(R.string.popularity),
    DATE(R.string.date),
}

const val DATE_SORTING_KEY_WORD = "publishedAt"
