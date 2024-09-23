package com.example.bookappreview.presentation.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
import com.example.bookappreview.presentation.model.mapper.toParcelableList
import kotlinx.coroutines.launch

class HomeViewModel(
    private val buscaLivros: BuscarLivrosUseCase
) : ViewModel(){

    private val _livros = MutableLiveData<List<LivroParcelable>>()
    val livros: LiveData<List<LivroParcelable>> get() = _livros

    private val allLivros = mutableListOf<LivroParcelable>()

    fun fetchBooks(searchQuery : String, context: Context) {
        viewModelScope.launch {
            val books = buscaLivros(searchQuery,context)
            _livros.postValue(books.toParcelableList())
        }
    }

    fun fetchBooksRecomendados(queries: List<String>, context: Context) {
        viewModelScope.launch {
            for (query in queries) {
                val books = buscaLivros(query, context)
                allLivros.addAll(books.toParcelableList()) // Acumule os livros
                // Você pode opcionalmente postar os livros acumulados após cada chamada
                _livros.postValue(allLivros)
            }
        }
    }

}