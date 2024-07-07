package com.android.know.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.os.bundleOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.android.know.ui.feature.article.ArticleScreen
import com.android.know.ui.feature.article.ArticleViewModel
import com.android.know.ui.feature.article.SavedArticleViewModel
import com.android.know.ui.feature.home.HomeScreen
import com.android.know.ui.feature.home.HomeViewModel
import com.android.know.ui.feature.saved.SavedArticlesScreen
import com.android.know.ui.feature.saved.SavedArticlesViewModel
import com.android.know.ui.feature.search.SearchScreen
import com.android.know.ui.feature.search.SearchViewModel
import com.android.know.ui.navigate
import org.koin.androidx.compose.getViewModel

@Composable
fun NavHost(navController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = NEWS_SCREEN
    ) {
        composable(NEWS_SCREEN) {
            val viewModel = getViewModel<HomeViewModel>()
            val homeData by viewModel.homeScreenData.collectAsStateWithLifecycle()
            HomeScreen(
                homeScreenData = homeData,
                onCategoryClick = viewModel::setCategory,
                onArticleClick = { navController.navigate(ARTICLE_SCREEN, bundleOf(NavigationParams.ID to it)) },
                onArticleSave = viewModel::saveArticle,
                onSearchClick = { navController.navigate(SEARCH_SCREEN) },
                onSavedClick = { navController.navigate(SAVED_ARTICLES_SCREEN) },
                setScrolledPosition = viewModel::getScrolledPosition
            )
        }
        composable(ARTICLE_SCREEN) { backStackEntry ->
            val viewModel = getViewModel<ArticleViewModel>()
            LaunchedEffect(null) {
                viewModel.getArticle(backStackEntry.arguments?.getString(NavigationParams.ID).orEmpty())
            }
            val article by viewModel.articleState.collectAsStateWithLifecycle()
            ArticleScreen(articleEntity = article)
        }
        composable(SEARCH_SCREEN) {
            val viewModel = getViewModel<SearchViewModel>()
            val searchData by viewModel.searchUIState.collectAsStateWithLifecycle()

            SearchScreen(
                searchData = searchData,
                onSearchValueChange = viewModel::onValueChange,
                onSearch = viewModel::onSearch,
                onRemoveClick = viewModel::onRemove,
                onSortClick = viewModel::onSortClick,
                onReadMore = { navController.navigate(ARTICLE_SCREEN, bundleOf(NavigationParams.ID to it)) },
                setScrolledPosition = viewModel::getScrolledPosition
            )
        }
        composable(SAVED_ARTICLE_SCREEN) { backStackEntry ->
            val viewModel = getViewModel<SavedArticleViewModel>()
            LaunchedEffect(null) {
                viewModel.getArticleById(backStackEntry.arguments?.getInt(NavigationParams.ROOM_ID) ?: 0)
            }
            val article by viewModel.articleState.collectAsStateWithLifecycle()
            ArticleScreen(articleEntity = article)
        }
        composable(SAVED_ARTICLES_SCREEN) {
            val viewModel = getViewModel<SavedArticlesViewModel>()
            val articles by viewModel.articlesState.collectAsStateWithLifecycle()
            SavedArticlesScreen(
                articles = articles,
                onArticleClick = { navController.navigate(SAVED_ARTICLE_SCREEN, bundleOf(NavigationParams.ROOM_ID to it)) })
        }
    }
}