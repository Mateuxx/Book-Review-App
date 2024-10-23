package com.example.bookappreview.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookappreview.presentation.screens.BookDetailsScreen
import com.example.bookappreview.presentation.screens.BooksScreen
import com.example.bookappreview.presentation.screens.ListScreen
import com.example.bookappreview.presentation.screens.ProfileScreen
import com.example.bookappreview.presentation.screens.ReviewsScreen
import com.example.bookappreview.presentation.screens.SearchBookScreen
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel

@Composable
fun NavGraph(navController: NavHostController, sharedViewModel: BookSharedViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Books.route
    ) {
        composable(
            route = Screen.Books.route,
            //animações entre as transições
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() }
        ) {
            BooksScreen(navController)
        }
        composable(Screen.Reviews.route) {
            ReviewsScreen(
                navController,
                sharedViewModel
            )
        }
        composable(Screen.Lists.route) { ListScreen(navController) }
        composable(route = Screen.Search.route) {
            SearchBookScreen(
                navController = navController,
                sharedViewModel = sharedViewModel // Passa o SharedViewModel aqui
            )
        }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
        composable(route = Screen.Details.route) {
            BookDetailsScreen(
                navController = navController,
                sharedViewModel = sharedViewModel // Também passa o SharedViewModel aqui
            )
        }
    }
}

