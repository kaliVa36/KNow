package com.android.know.ui.feature.saved

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.know.R
import com.android.know.domain.entity.ArticleEntity
import com.android.know.ui.components.text.Heading
import com.android.know.ui.feature.ArticleSummaryUI
import com.android.know.ui.feature.ArticleSummaryWithImageUI

@Composable
fun SavedArticlesScreen(articles: List<ArticleEntity>, onArticleClick: (Int) -> Unit) {
    BoxWithConstraints {
        val height = maxHeight
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Heading(text = stringResource(id = R.string.saved_articles))
            Spacer(modifier = Modifier.height(12.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                articles.forEach { article ->
                    if (article.url.isNotBlank()) {
                        ArticleSummaryWithImageUI(
                            article = article,
                            height = height / 2,
                            onClick = { onArticleClick(article.roomId) },
                            onSave = { },
                            isSaved = true
                        )
                    } else {
                        ArticleSummaryUI(
                            article = article,
                            height = height / 2,
                            onClick = { onArticleClick(article.roomId) },
                            onSave = { },
                            isSaved = true
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}