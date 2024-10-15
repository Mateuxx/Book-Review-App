package com.example.bookappreview.presentation.navigation.handlers

import androidx.navigation.NavController
import com.example.bookappreview.presentation.navigation.Screen

fun handleTabNavigation(navController: NavController, selectedTabIndex: Int) {
    val route = when (selectedTabIndex) {
        0 -> Screen.Books.route
        1 -> Screen.Reviews.route
        2 -> Screen.Lists.route
        else -> Screen.Books.route
    }

    navController.navigate(route) {
        restoreState = true
        launchSingleTop = true
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
    }
}