package com.example.bookappreview.presentation.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.data.repository.UsuarioRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UsuarioRepository
) : ViewModel(){

    private val _livros = MutableLiveData<List<LivroParcelable>>()
    val livros: LiveData<List<LivroParcelable>> get() = _livros

    fun fetchBooks(searchQuery : String, context: Context) {
        viewModelScope.launch {
            val books = repository.fetchBooks(searchQuery,context)
            _livros.postValue(books)
        }
    }
}