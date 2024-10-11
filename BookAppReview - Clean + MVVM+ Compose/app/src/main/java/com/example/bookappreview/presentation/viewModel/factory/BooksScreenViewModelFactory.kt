package com.example.bookappreview.presentation.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
import com.example.bookappreview.presentation.viewModel.BooksScreenViewModel

class BooksScreenViewModelFactory (
    private val buscaLivrosUseCase: BuscarLivrosUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksScreenViewModel::class.java)) {
            return BooksScreenViewModel(buscaLivrosUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
