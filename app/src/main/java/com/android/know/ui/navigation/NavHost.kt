package com.android.know.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.android.know.ui.DummyScreen
import com.android.know.ui.feature.HomeScreen
import com.android.know.ui.feature.HomeViewModel
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
            HomeScreen(homeScreenData = homeData)
        }
        composable(ARTICLE_SCREEN) {
            DummyScreen(screen = "Article screen")
        }
    }
}