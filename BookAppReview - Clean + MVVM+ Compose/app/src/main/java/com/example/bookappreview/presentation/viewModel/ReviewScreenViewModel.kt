package com.example.bookappreview.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.presentation.states.ReviewScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReviewScreenViewModel : ViewModel() {

    //Encapsulamento do estado da ui
    private val _uiState = MutableStateFlow(ReviewScreenUiState()) //privado
    val uiState: StateFlow<ReviewScreenUiState> = _uiState.asStateFlow()

    fun initializeBook(book: LivroParcelable) {
        _uiState.value = _uiState.value.copy(book = book)
    }

    fun updateRating(newRating: Int) {
        _uiState.value = _uiState.value.copy(rating = newRating)
    }

    fun toggleLiked() {
        _uiState.value = _uiState.value.copy(liked = !_uiState.value.liked)
    }

    fun updateReviewText(newText: String) {
        _uiState.value = _uiState.value.copy(reviewString = newText)
    }
}