package com.android.know.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.android.know.ui.DummyScreen

@Composable
fun NavHost(navController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = NEWS_SCREEN
    ) {
        composable(NEWS_SCREEN) {
            DummyScreen("News screen") { navController.navigate(ARTICLE_SCREEN) }
        }
        composable(ARTICLE_SCREEN) {
            DummyScreen(screen = "Article screen")
        }
    }
}