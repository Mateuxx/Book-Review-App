package com.example.bookappreview.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.bookappreview.presentation.states.BookDetailsScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailsScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BookDetailsScreenUiState())
    val uiState: StateFlow<BookDetailsScreenUiState> = _uiState

    fun toggleLike() {
        _uiState.value = _uiState.value.copy(isLiked = !_uiState.value.isLiked)
    }

    fun toggleWatch() {
        _uiState.value = _uiState.value.copy(isWatched = !_uiState.value.isWatched)
    }

    fun setRating(rating: Int) {
        _uiState.value = _uiState.value.copy(rating = rating)
    }
}