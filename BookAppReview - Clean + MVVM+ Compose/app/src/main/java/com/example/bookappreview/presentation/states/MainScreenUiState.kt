package com.example.bookappreview.presentation.states

data class MainScreenUiState(
    val selectedBottomNavIndex: Int = 0,
    val selectedTabIndex: Int = 0,
    val currentScreen: Screen = Screen.Books // iniciada em screen Books
)

sealed class Screen {

    data object Books : Screen()
    data object Reviews : Screen()
    data object Lists : Screen()
    data object Search : Screen()
    data object Profile : Screen()
}