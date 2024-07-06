package com.android.know.ui.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.know.R
import com.android.know.ui.components.category.ArticleCategories
import com.android.know.ui.components.category.CategoryUI
import com.android.know.ui.components.text.Heading

@Composable
fun HomeScreen(
    homeScreenData: HomeScreenData,
    onCategoryClick: (ArticleCategories) -> Unit
) {
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
            Heading(text = stringResource(id = R.string.top_headlines))
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(ArticleCategories.entries) {
                    Spacer(modifier = Modifier.width(4.dp))
                    CategoryUI(category = stringResource(id = it.value), onClick = { onCategoryClick(it) }, isSelected = it == homeScreenData.selectedCategory)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                homeScreenData.articles.forEach {
                    if (it.url.isNotBlank()) {
                        ArticleSummaryWithImageUI(article = it, height = height / 2)
                    } else {
                        ArticleSummaryUI(article = it, height = height / 2)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}
