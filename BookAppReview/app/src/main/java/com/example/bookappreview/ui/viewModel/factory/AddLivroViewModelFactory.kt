package com.example.bookappreview.ui.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookappreview.repository.MainRepository
import com.example.bookappreview.ui.viewModel.AddLivroViewModel
import com.example.bookappreview.ui.viewModel.UsuarioViewModel

/**
 *  O factory cria o viewModel e coloca seus construtores com argumentos.
 */
class AddLivroViewModelFactory(
    private val repository: MainRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AddLivroViewModel::class.java)) {
            AddLivroViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}