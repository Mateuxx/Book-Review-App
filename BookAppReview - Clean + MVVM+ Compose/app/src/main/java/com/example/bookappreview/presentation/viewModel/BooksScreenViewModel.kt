package com.example.bookappreview.presentation.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookappreview.data.webclient.aiservice.AiService
import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
import com.example.bookappreview.domain.usecase.livro.GetBookFromDatabaseUseCase
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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksScreenViewModel @Inject constructor(
    private val buscarLivrosUseCase: BuscarLivrosUseCase,
    private val getBookFromDatabaseUseCase: GetBookFromDatabaseUseCase,
    private val aiService: AiService
) : ViewModel() {

    private val _uiState = MutableStateFlow<BooksScreenUiState>(BooksScreenUiState.Loading)
    val uiState: StateFlow<BooksScreenUiState> = _uiState.asStateFlow()

    // TODO: Refactor for use a ui case latter for this, better for clean?
    // Função para atualizar os livros, incluindo um novo livro salvo
    fun refreshBooks(searchQuery: String, context: Context) {
        fetchLastSavedBooksAndCompleteWithApi(searchQuery, context)
    }

    fun fetchLastSavedBooksAndCompleteWithApi(searchQuery: String, context: Context) {
        viewModelScope.launch {
            _uiState.value = BooksScreenUiState.Loading
            Log.d("BooksScreenViewModel", "Iniciando busca dos livros salvos e completando com API")

            getBookFromDatabaseUseCase()
                .combine(buscarLivrosUseCase(searchQuery, context)) { savedBooks, apiBooks ->
                    // Ordena os livros salvos para garantir que os mais recentes apareçam primeiro
                    val orderedSavedBooks = savedBooks.sortedByDescending { it.dateReview }
                    val finalBookList = if (orderedSavedBooks.size >= 5) {
                        orderedSavedBooks.toParcelableList()
                    } else {
                        orderedSavedBooks.toParcelableList() + apiBooks.toParcelableList()
                            .take(5 - orderedSavedBooks.size)
                    }
                    Log.d(
                        "BooksScreenViewModel",
                        "Livros finais após combinar: ${finalBookList.size}"
                    )
                    finalBookList.forEach { livro ->
                        Log.d("BooksScreenViewModel", "Livro: ${livro.title}, Data: ${livro.year}")
                    }

                    BooksScreenUiState.Success(
                        livros = finalBookList,
                        livrosRecomendados = emptyList()
                    )
                }
                .catch { e ->
                    _uiState.value =
                        BooksScreenUiState.Error("Erro ao carregar livros: ${e.message}")
                }
                .collect { state ->
                    _uiState.value = state
                    Log.d(
                        "BooksScreenViewModel",
                        "Estado de UI atualizado com livros mais recentes"
                    )
                }
        }
    }


    fun fetchBooksRecomendados(recommendations: List<String>, context: Context) {
        viewModelScope.launch {
            try {
                val recommendedBooks = mutableListOf<LivroParcelable>()
                recommendations.map { query ->
                    async {
                        buscarLivrosUseCase(query, context).collect { livros ->
                            recommendedBooks.addAll(livros.toParcelableList())
                        }
                    }
                }.awaitAll()

                val currentBooks =
                    (_uiState.value as? BooksScreenUiState.Success)?.livros ?: emptyList()
                _uiState.value = BooksScreenUiState.Success(
                    livros = currentBooks,
                    livrosRecomendados = recommendedBooks
                )
            } catch (e: Exception) {
                _uiState.value =
                    BooksScreenUiState.Error("Erro ao buscar livros recomendados: ${e.message}")
            }
        }
    }

    suspend fun aiRecommendation(book: String): List<String> {
        val queries = aiService.fetchBookRecommendations(book)
        return aiService.parseBookRecommendations(queries)
    }
}