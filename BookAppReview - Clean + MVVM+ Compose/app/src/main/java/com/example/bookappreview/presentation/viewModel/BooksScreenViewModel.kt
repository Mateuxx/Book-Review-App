package com.example.bookappreview.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookappreview.data.webclient.aiservice.AiService
import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.presentation.model.mapper.toParcelableList
import com.example.bookappreview.presentation.states.BooksScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksScreenViewModel @Inject constructor(
    private val buscarLivrosUseCase: BuscarLivrosUseCase,
    private val aiService: AiService // Inject AiService as well
) : ViewModel() {

    private val _uiState = MutableStateFlow<BooksScreenUiState>(BooksScreenUiState.Loading)
    val uiState: StateFlow<BooksScreenUiState> = _uiState.asStateFlow()

    fun fetchBooks(searchQuery: String, context: Context) {
        if (_uiState.value is BooksScreenUiState.Success && (_uiState.value as BooksScreenUiState.Success).livros.isNotEmpty()) {
            return
        }

        viewModelScope.launch {
            _uiState.value = BooksScreenUiState.Loading

            buscarLivrosUseCase(searchQuery, context)
                .catch { e ->
                    _uiState.value = BooksScreenUiState.Error("Erro ao buscar livros: ${e.message}")
                }
                .collect { books ->
                    val livrosRecomendados = (_uiState.value as? BooksScreenUiState.Success)?.livrosRecomendados ?: emptyList()
                    _uiState.value = BooksScreenUiState.Success(
                        livros = books.toParcelableList(),
                        livrosRecomendados = livrosRecomendados
                    )
                }
        }
    }

    fun fetchBooksRecomendados(queries: List<String>, context: Context) {
        if (_uiState.value is BooksScreenUiState.Success && (_uiState.value as BooksScreenUiState.Success).livrosRecomendados.isNotEmpty()) {
            return
        }

        viewModelScope.launch {
            try {
                val recommendedBooks = mutableListOf<LivroParcelable>()
                queries.map { query ->
                    async {
                        buscarLivrosUseCase(query, context).collect { livros ->
                            recommendedBooks.addAll(livros.toParcelableList())
                        }
                    }
                }.awaitAll()

                val livros = (_uiState.value as? BooksScreenUiState.Success)?.livros ?: emptyList()
                _uiState.value = BooksScreenUiState.Success(
                    livros = livros,
                    livrosRecomendados = recommendedBooks
                )
            } catch (e: Exception) {
                _uiState.value = BooksScreenUiState.Error("Erro ao buscar livros recomendados: ${e.message}")
            }
        }
    }

    suspend fun aiRecommendation(book: String): List<String> {
        val queries = aiService.fetchBookRecommendations(book)
        return aiService.parseBookRecommendations(queries)
    }
}
