package com.android.know.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.know.R
import com.android.know.domain.entity.ArticleEntity
import com.android.know.ui.ImageContentDescription
import com.android.know.ui.components.category.ArticleCategories
import com.android.know.ui.components.category.CategoryUI
import com.android.know.ui.components.text.Heading
import com.android.know.ui.feature.ArticleSummaryUI
import com.android.know.ui.feature.ArticleSummaryWithImageUI

@Composable
fun HomeScreen(
    homeScreenData: HomeScreenData,
    onCategoryClick: (ArticleCategories) -> Unit,
    onArticleClick: (String) -> Unit,
    onArticleSave: (ArticleEntity) -> Unit,
    onSearchClick: () -> Unit,
    onSavedClick: () -> Unit,
    setScrolledPosition: (Int) -> Unit,
) {
    BoxWithConstraints {
        val height = maxHeight
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Heading(text = stringResource(id = R.string.top_headlines))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.bookmark),
                        contentDescription = ImageContentDescription.BOOKMARK,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onSavedClick() },
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.content_primary)
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = ImageContentDescription.SEARCH,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onSearchClick() },
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.content_primary))
                    )
                }

            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(ArticleCategories.entries) {
                    Spacer(modifier = Modifier.width(4.dp))
                    CategoryUI(
                        category = stringResource(id = it.value),
                        onClick = { onCategoryClick(it) },
                        isSelected = it == homeScreenData.selectedCategory
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            val listState = rememberLazyListState()
            LazyColumn(modifier = Modifier.fillMaxWidth(), state = listState) {
                itemsIndexed(homeScreenData.articles) { index, articleEntity ->
                    if (listState.isScrollInProgress) setScrolledPosition(index)
                    if (articleEntity.title != "[Removed]") {
                        if (articleEntity.url.isNotBlank()) {
                            ArticleSummaryWithImageUI(
                                article = articleEntity,
                                height = height / 2,
                                onClick = { id -> onArticleClick(id) },
                                onSave = { onArticleSave(articleEntity) },
                                isSaved = homeScreenData.savedArticles.data.contains(articleEntity)
                            )
                        } else {
                            ArticleSummaryUI(
                                article = articleEntity,
                                height = height / 2,
                                onClick = { id -> onArticleClick(id) },
                                onSave = { onArticleSave(articleEntity) },
                                isSaved = homeScreenData.savedArticles.data.contains(articleEntity)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}
