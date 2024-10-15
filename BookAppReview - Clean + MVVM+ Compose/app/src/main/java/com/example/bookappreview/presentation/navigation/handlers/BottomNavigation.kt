package com.example.bookappreview.presentation.navigation.handlers

import androidx.navigation.NavController
import com.example.bookappreview.presentation.navigation.Screen

fun handleBottomNavigation(navController: NavController, selectedBottomNavIndex: Int) {
    val route = when (selectedBottomNavIndex) {
        0 -> Screen.Books.route
        1 -> Screen.Search.route
        2 -> Screen.Profile.route
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