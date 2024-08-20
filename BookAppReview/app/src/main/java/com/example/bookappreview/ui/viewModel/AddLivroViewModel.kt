package com.example.bookappreview.ui.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookappreview.model.Livro
import com.example.bookappreview.model.Usuario
import com.example.bookappreview.repository.MainRepository
import kotlinx.coroutines.launch
import java.util.ArrayList

class AddLivroViewModel(
    private val repository: MainRepository
) : ViewModel() {


    private val _livros = MutableLiveData<List<Livro>>()
    val livros: LiveData<List<Livro>> get() = _livros

    fun fetchBooks(searchQuery: String, context: Context) {
        viewModelScope.launch {
            val books = repository.fetchBooks(searchQuery, context)
            _livros.postValue(books)
        }
    }


}