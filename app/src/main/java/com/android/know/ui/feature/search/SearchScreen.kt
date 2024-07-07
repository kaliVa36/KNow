package com.android.know.ui.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.know.R
import com.android.know.ui.components.SearchBar
import com.android.know.ui.components.sorting.ArticleSorting
import com.android.know.ui.components.sorting.SortingUI
import com.android.know.ui.components.text.Heading
import com.android.know.ui.components.text.TextMdRegular
import com.android.know.ui.feature.ArticleSummaryWithImageUI

@Composable
fun SearchScreen(
    searchData: SearchData,
    onSearchValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    onSortClick: (ArticleSorting) -> Unit,
    onRemoveClick: () -> Unit,
    onReadMore: (String) -> Unit
) {
    BoxWithConstraints {
        val height = maxHeight
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val (searchBar, content) = createRefs()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(searchBar) {
                        top.linkTo(parent.top)
                    }
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Heading(text = stringResource(id = R.string.search))
                Spacer(modifier = Modifier.height(12.dp))
                SearchBar(value = searchData.searchValue, onValueChange = onSearchValueChange, onSearch = { onSearch() })
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    item {
                        SortingUI(category = stringResource(id = R.string.sort_by))
                    }
                    items(ArticleSorting.entries) {
                        Spacer(modifier = Modifier.width(4.dp))
                        SortingUI(
                            category = stringResource(id = it.value),
                            onClick = { onSortClick(it) },
                            isSelected = it == (searchData.selectedSorting ?: 0),
                            onRemoveClick = onRemoveClick
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(content) {
                        top.linkTo(searchBar.bottom)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                if (searchData.articles.isNotEmpty()) {
                    searchData.articles.forEach { article ->
                        ArticleSummaryWithImageUI(article = article, height = height / 2) { onReadMore(article.id) }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        TextMdRegular(text = stringResource(id = R.string.search_empty_placeholder),)
                    }
                }
            }
        }
    }
}
