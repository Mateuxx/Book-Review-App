package com.example.bookappreview.presentation.navigation

sealed class Screen(val route: String) {
    data object Books : Screen("books")
    data object Reviews : Screen("reviews")
    data object Lists : Screen("lists")
    data object Search : Screen("search")
    data object Profile : Screen("profile")
}
