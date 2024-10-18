package com.example.bookappreview.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bookappreview.presentation.model.LivroParcelable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class BookSharedViewModel : ViewModel() {

    // Fluxo encapsulado do livro selecionado
    private val _selectedBook = MutableStateFlow<LivroParcelable?>(null)
    val selectedBook: StateFlow<LivroParcelable?> = _selectedBook.asStateFlow()

    // Função para selecionar um livro
    fun selectBook(book: LivroParcelable) {
        _selectedBook.value = book // Atribui diretamente o livro selecionado
        Log.i("TAG", "Livro selecionado no SharedViewModel: $book")
    }

    // Função para deselecionar o livro
    fun deselectBook() {
        _selectedBook.value = null
    }
}
