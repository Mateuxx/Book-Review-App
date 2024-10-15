package com.example.bookappreview.presentation.states

data class MainScreenUiState(
    val selectedBottomNavIndex: Int = 0,  // Controla a aba inferior selecionada (Home, Search, Profile)
    val selectedTabIndex: Int = 0         // Controla a aba superior selecionada (Books, Reviews, Lists)
)
sealed class Screen(val route: String) {
    data object Books : Screen("books")
    data object Reviews : Screen("reviews")
    data object Lists : Screen("lists")
    data object Search : Screen("search")
    data object Profile : Screen("profile")
}