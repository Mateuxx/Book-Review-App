package com.example.bookappreview.presentation.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
import com.example.bookappreview.presentation.model.mapper.toParcelableList
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class HomeViewModel(
    private val buscaLivros: BuscarLivrosUseCase
) : ViewModel() {

    private val _livros = MutableLiveData<List<LivroParcelable>>()
    val livros: LiveData<List<LivroParcelable>> get() = _livros

    private val _livrosRecomendados = MutableLiveData<List<LivroParcelable>>()
    val livrosRecomendados: LiveData<List<LivroParcelable>> get() = _livrosRecomendados

    private val allLivrosRecomendados = mutableListOf<LivroParcelable>()

    fun fetchBooks(searchQuery: String, context: Context) {
        viewModelScope.launch {
            val books = buscaLivros(searchQuery, context)
            _livros.postValue(books.toParcelableList()) // Atualiza o LiveData dos livros principais
        }
    }

    /**
     * Async and map Improves performance
     */
    fun fetchBooksRecomendados(queries: List<String>, context: Context) {
        viewModelScope.launch {

            allLivrosRecomendados.clear() //limpa todos os livros anteriores
            val deferredResults = queries.map { query ->
                async {
                    buscaLivros(query, context)
                }
            }
            // coleta dos dados de todas as courotines executadas em async
            // espera todas as busca por livros terminarem e retorna uma lista com esses resultados
            val resultList = deferredResults.awaitAll()

            resultList.forEach { books ->
                Log.i("TAG", "fetchBooksRecomendados: $books")
                allLivrosRecomendados.addAll(books.toParcelableList())
            }
            _livrosRecomendados.postValue(allLivrosRecomendados)
        }
    }

}
