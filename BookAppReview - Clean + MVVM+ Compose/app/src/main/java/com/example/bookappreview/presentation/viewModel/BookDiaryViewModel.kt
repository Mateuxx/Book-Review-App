package com.example.bookappreview.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookappreview.domain.usecase.livro.GetAllBooksGroupedByDateUseCase
import com.example.bookappreview.presentation.states.BookDiaryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDiaryViewModel @Inject constructor(
    private val getAllBooksGroupedByDateUseCase: GetAllBooksGroupedByDateUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookDiaryUiState())
    val uiState: StateFlow<BookDiaryUiState> = _uiState

    init {
        loadGroupedBooks()
    }

    private fun loadGroupedBooks() {
        viewModelScope.launch {
            //cada vez que o flow emite um novo agrupamento de livros o bloco de código é executado
            getAllBooksGroupedByDateUseCase().collect { groupedBooks ->
                _uiState.value = BookDiaryUiState(
                    groupedReviews = groupedBooks,
                    isLoading = false
                )
            }
        }
    }

}