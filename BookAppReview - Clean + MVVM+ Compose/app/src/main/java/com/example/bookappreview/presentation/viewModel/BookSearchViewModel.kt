package com.example.bookappreview.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
import com.example.bookappreview.presentation.model.mapper.toParcelableList
import com.example.bookappreview.presentation.states.SearchScreenUiState
import com.example.bookappreview.presentation.states.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookSearchViewModel(
    private val buscarLivrosUseCase: BuscarLivrosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenUiState())
    val uiState: StateFlow<SearchScreenUiState> = _uiState.asStateFlow()

    // Atualiza a query no estado da UI e dispara a busca de livros
    fun onQueryChanged(newQuery: String, context: Context) {
        _uiState.update { it.copy(query = newQuery, searchState = SearchState.Loading) }

        // Dispara a busca de livros imediatamente ao mudar a query
        searchBooks(newQuery, context)
    }

    //Busca de Livros
    private fun searchBooks(query: String, context: Context) {
        if (query.isNotEmpty()) {
            // Dispara a busca de livros
            viewModelScope.launch {
                buscarLivrosUseCase(query, context)
                    .catch { e ->
                        _uiState.update { it.copy(searchState = SearchState.Error("Erro ao buscar livros: ${e.message}")) }
                    }
                    .collect { livros ->
                        _uiState.update { it.copy(searchState = SearchState.Success(livros.toParcelableList())) }
                    }
            }
        } else {
            _uiState.update { it.copy(searchState = SearchState.Idle) }
        }
    }

    // Reinicializa o estado para Idle
    fun resetState() {
        _uiState.value = SearchScreenUiState()
    }
}
