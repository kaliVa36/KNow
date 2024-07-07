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
import com.android.know.ui.feature.home.HomeScreen
import com.android.know.ui.feature.home.HomeViewModel
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
                onArticleSave = viewModel::saveArticle
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
    }
}