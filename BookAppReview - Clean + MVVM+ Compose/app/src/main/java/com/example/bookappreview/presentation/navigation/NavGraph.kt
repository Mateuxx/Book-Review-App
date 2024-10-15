package com.example.bookappreview.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookappreview.presentation.screens.BooksScreen
import com.example.bookappreview.presentation.screens.ListScreen
import com.example.bookappreview.presentation.screens.ProfileScreen
import com.example.bookappreview.presentation.screens.ReviewsScreen
import com.example.bookappreview.presentation.screens.SearchBookScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Books.route
    ) {
        composable(
            route = Screen.Books.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() }
        ) {
            BooksScreen(navController)
        }
        composable(Screen.Reviews.route) { ReviewsScreen(navController) }
        composable(Screen.Lists.route) { ListScreen(navController) }
        composable(Screen.Search.route) { SearchBookScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
    }
}

