//package com.example.bookappreview.presentation.viewModel
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.bookappreview.presentation.model.LivroParcelable
//import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
//import com.example.bookappreview.presentation.model.mapper.toParcelableList
//import kotlinx.coroutines.launch
//
//class AddLivroViewModel(
//    private val buscaLivros: BuscarLivrosUseCase
//) : ViewModel() {
//
//
//    private val _livros = MutableLiveData<List<LivroParcelable>>()
//    val livros: LiveData<List<LivroParcelable>> get() = _livros
//
//    fun fetchBooks(searchQuery: String, context: Context) {
//        viewModelScope.launch {
//            val books = buscaLivros(searchQuery, context)
//            _livros.postValue(books.toParcelableList())
//        }
//    }
//}