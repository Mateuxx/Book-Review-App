package com.example.bookappreview.presentation.states

data class BookDetailsScreenUiState(
    val isLiked: Boolean = false,
    val isWatched: Boolean = false,
    val rating: Int = 0
)
