package com.example.bookappreview.presentation.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookappreview.data.webclient.aiservice.AiService
import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.presentation.model.mapper.toParcelableList
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BooksScreenViewModel(

    private val buscaLivros: BuscarLivrosUseCase
) : ViewModel() {

    private val aiService = AiService()

    // Estadoo para armazenar
    private val _livros = MutableStateFlow<List<LivroParcelable>>(emptyList())
    val livros: StateFlow<List<LivroParcelable>> = _livros.asStateFlow()

    // Estado para armazenar a lista de livros recomendados

    //variavel privada no qual eu posso modificar
    private val _livrosRecomendados = MutableStateFlow<List<LivroParcelable>>(emptyList())

    //variabvel publica na qual eu posso modificar - Ai recommendation
    val livrosRecomendados: StateFlow<List<LivroParcelable>> = _livrosRecomendados.asStateFlow()

    //Google search books api
    fun fetchBooks(searchQuery: String, context: Context) {
        viewModelScope.launch {
            val books = buscaLivros(searchQuery, context)
            Log.i("TAG", "fetchBooks: $books")
            _livros.update { books.toParcelableList() } // Atualiza o StateFlow dos livros populares
        }
    }

    // Ai Recommendation
    fun fetchBooksRecomendados(queries: List<String>, context: Context) {
        viewModelScope.launch {
            val deferredResults = queries.map { query ->
                async {
                    buscaLivros(query, context)
                }
            }
            val resultList = deferredResults.awaitAll()
            Log.i("TAG", "fetchBooksRecomendados Lista do tipo livros: $resultList")
            val recommendedBooks = resultList.flatMap { it.toParcelableList() }
            Log.i("TAG", "fetchBooksRecomendados Livros Recomendados: $recommendedBooks")
            _livrosRecomendados.value = recommendedBooks // Atualiza o StateFlow dos livros recomendados
        }
    }


    suspend fun aiRecommendation(book: String): List<String> {
        val queries = aiService.fetchBookRecommendations(book)
        val teste = aiService.parseBookRecommendations(queries)
        return teste
    }


}
