package com.example.bookappreview.presentation.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookappreview.data.repository.UsuarioRepositoryImpl
import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
import com.example.bookappreview.presentation.viewModel.AddLivroViewModel

/**
 *  O factory cria o viewModel e coloca seus construtores com argumentos.
 */
class AddLivroViewModelFactory(
    private val usecase: BuscarLivrosUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AddLivroViewModel::class.java)) {
            AddLivroViewModel(this.usecase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}