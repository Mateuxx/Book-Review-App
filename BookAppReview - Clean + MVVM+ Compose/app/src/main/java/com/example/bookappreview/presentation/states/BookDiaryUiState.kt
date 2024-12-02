package com.example.bookappreview.presentation.states

import com.example.bookappreview.domain.model.Livro

data class BookDiaryUiState (
    val groupedReviews: Map<String, List<Livro>> = emptyMap(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)