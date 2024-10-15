package com.example.bookappreview.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookappreview.data.webclient.aiservice.AiService
import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.presentation.model.mapper.toParcelableList
import com.example.bookappreview.presentation.states.BooksScreenUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


// TODO: Fazer o ui state e colocar E refatorar esse viewmodel e o bookScreen
class BooksScreenViewModel(
    private val buscarLivrosUseCase: BuscarLivrosUseCase
) : ViewModel() {

    private val aiService = AiService() // Instância do serviço de recomendação

    // Estado encapsulado do UI State
    private val _uiState = MutableStateFlow<BooksScreenUiState>(BooksScreenUiState.Loading) // Estado inicial de loading
    val uiState: StateFlow<BooksScreenUiState> = _uiState.asStateFlow()

    // Google search books API
    fun fetchBooks(searchQuery: String, context: Context) {
        viewModelScope.launch {
            // Muda o estado para 'Loading'
            _uiState.value = BooksScreenUiState.Loading

            buscarLivrosUseCase(searchQuery, context)
                .catch { e ->
                    // Em caso de erro, atualize o estado de erro
                    _uiState.value = BooksScreenUiState.Error("Erro ao buscar livros: ${e.message}")
                }
                .collect { books ->
                    // Preserva os livros recomendados e atualiza apenas a lista de livros
                    val currentState = _uiState.value
                    val livrosRecomendados = if (currentState is BooksScreenUiState.Success) {
                        currentState.livrosRecomendados
                    } else {
                        emptyList()
                    }

                    // Atualiza o estado de sucesso com os livros buscados
                    _uiState.value = BooksScreenUiState.Success(
                        livros = books.toParcelableList(),
                        livrosRecomendados = livrosRecomendados
                    )
                }
        }
    }

    // Recomendação via AI
    fun fetchBooksRecomendados(queries: List<String>, context: Context) {
        viewModelScope.launch {
            // Muda o estado para 'Loading'
            _uiState.value = BooksScreenUiState.Loading

            try {
                val recommendedBooks = mutableListOf<LivroParcelable>()

                // Executa cada query em paralelo e coleta os resultados
                queries.map { query ->
                    async {
                        buscarLivrosUseCase(query, context).collect { livros ->
                            // Coleta os livros emitidos pelo Flow e adiciona à lista de recomendados
                            recommendedBooks.addAll(livros.toParcelableList())
                        }
                    }
                }.awaitAll() // Aguarda todas as operações finalizarem

                // Preserva os livros principais e atualiza apenas os livros recomendados
                val currentState = _uiState.value
                val livros = if (currentState is BooksScreenUiState.Success) {
                    currentState.livros
                } else {
                    emptyList()
                }

                // Atualiza o estado de sucesso com os livros recomendados
                _uiState.value = BooksScreenUiState.Success(
                    livros = livros, // Preserva a lista de livros
                    livrosRecomendados = recommendedBooks // Atualiza a lista de livros recomendados
                )
            } catch (e: Exception) {
                // Atualiza o estado com a mensagem de erro
                _uiState.value =
                    BooksScreenUiState.Error("Erro ao buscar livros recomendados: ${e.message}")
            }
        }
    }

    // Função para buscar recomendações via AI
    suspend fun aiRecommendation(book: String): List<String> {
        // Obtém as recomendações de livros usando o serviço de AI
        val queries = aiService.fetchBookRecommendations(book)
        // Analisa as recomendações e retorna a lista de consultas
        return aiService.parseBookRecommendations(queries)
    }

}
