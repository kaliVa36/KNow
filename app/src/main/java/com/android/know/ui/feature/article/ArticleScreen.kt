package com.android.know.ui.feature.article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.android.know.R
import com.android.know.domain.entity.ArticleEntity
import com.android.know.ui.components.text.TextSmMedium
import com.android.know.ui.feature.ArticleSummaryUI
import com.android.know.ui.feature.ArticleSummaryWithImageUI

@Composable
fun ArticleScreen(articleEntity: ArticleEntity) {
    BoxWithConstraints {
        val height = maxHeight
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
        ) {
            if (articleEntity.url.isNotBlank()) {
                ArticleSummaryWithImageUI(article = articleEntity, height = height / 2, isArticleSummary = false)
            } else {
                ArticleSummaryUI(article = articleEntity, height = height / 2, isArticleSummary = false)
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextSmMedium(text = articleEntity.description)
        }
    }
}