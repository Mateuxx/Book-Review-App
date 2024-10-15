package com.example.bookappreview.presentation.states

import com.example.bookappreview.presentation.model.LivroParcelable

sealed class BooksScreenUiState {

    // Loading state (inicial)
    data object Loading : BooksScreenUiState()

    // Success state (com dados)
    data class Success(
        val livros: List<LivroParcelable>, // Lista de livros
        val livrosRecomendados: List<LivroParcelable>  // Lista de livros recomendados
    ) : BooksScreenUiState()

    data class Error(val message: String) : BooksScreenUiState()
}