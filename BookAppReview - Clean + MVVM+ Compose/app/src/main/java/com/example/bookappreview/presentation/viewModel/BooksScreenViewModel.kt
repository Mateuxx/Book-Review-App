package com.example.bookappreview.presentation.viewModel

import android.content.Context
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
    // Atualiza os livros, mantendo os recomendados no estado UI
    fun refreshBooks(searchQuery: String, context: Context) {
        viewModelScope.launch {
            _uiState.value = BooksScreenUiState.Loading

            getBookFromDatabaseUseCase()
                .combine(buscarLivrosUseCase(searchQuery, context)) { savedBooks, apiBooks ->
                    val finalBookList = if (savedBooks.size >= 5) {
                        savedBooks.toParcelableList()
                    } else {
                        savedBooks.toParcelableList() + apiBooks.toParcelableList()
                            .take(5 - savedBooks.size)
                    }
                    val currentRecommendedBooks =
                        (_uiState.value as? BooksScreenUiState.Success)?.livrosRecomendados
                            ?: emptyList()

                    BooksScreenUiState.Success(
                        livros = finalBookList,
                        livrosRecomendados = currentRecommendedBooks // Mantém os recomendados inalterados
                    )
                }
                .catch { e ->
                    _uiState.value =
                        BooksScreenUiState.Error("Erro ao carregar livros: ${e.message}")
                }
                .collect { state ->
                    _uiState.value = state
                }
        }
    }


    // Carrega livros recomendados e atualiza o estado da UI diretamente
    fun fetchBooksRecomendados(recommendations: List<String>, context: Context) {
        viewModelScope.launch {
            try {
                val recommendedBooks = mutableListOf<LivroParcelable>()

                // Realiza cada busca em paralelo e converte para LivroParcelable após coletar
                recommendations.map { query ->
                    async {
                        buscarLivrosUseCase(query, context).collect { livros ->
                            recommendedBooks.addAll(livros.toParcelableList())
                        }
                    }
                }.awaitAll() // Aguarda todas as buscas paralelas finalizarem

                // Atualiza o estado de UI mantendo os livros atuais e adicionando os recomendados
                val currentBooks = (_uiState.value as? BooksScreenUiState.Success)?.livros ?: emptyList()
                _uiState.value = BooksScreenUiState.Success(
                    livros = currentBooks,
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