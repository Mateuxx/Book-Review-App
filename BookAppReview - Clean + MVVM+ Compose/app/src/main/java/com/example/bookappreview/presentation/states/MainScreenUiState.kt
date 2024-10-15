package com.example.bookappreview.presentation.states

data class MainScreenUiState(
    val selectedBottomNavIndex: Int = 0,  // Controla a aba inferior selecionada (Home, Search, Profile)
    val selectedTabIndex: Int = 0         // Controla a aba superior selecionada (Books, Reviews, Lists)
)
