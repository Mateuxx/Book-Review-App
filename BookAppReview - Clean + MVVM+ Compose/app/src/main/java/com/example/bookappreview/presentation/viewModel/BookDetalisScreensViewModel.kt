package com.example.bookappreview.presentation.viewModel

import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.presentation.states.BookDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookDetalisScreensViewModel {
    private val _uiState = MutableStateFlow(BookDetailsUiState())
    val uiState: StateFlow<BookDetailsUiState> = _uiState.asStateFlow()


    //aualizar os detalhes dos livros
    fun loadBookDetalis(book: LivroParcelable) {
        _uiState.value = BookDetailsUiState(
            title = book.title ?: "Título desconhecido",
            author = book.autor ?: "Autor desconhecido",
            year = book.year ?: "Ano desconhecido",
            description = book.description ?: "Descrição indisponível"
        )
    }
}