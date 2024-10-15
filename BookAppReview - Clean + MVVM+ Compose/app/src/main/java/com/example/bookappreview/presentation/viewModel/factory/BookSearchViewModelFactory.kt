package com.example.bookappreview.presentation.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
import com.example.bookappreview.presentation.viewModel.BookSearchViewModel

class BookSearchViewModelFactory(
    private val buscarLivrosUseCase: BuscarLivrosUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookSearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookSearchViewModel(buscarLivrosUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}