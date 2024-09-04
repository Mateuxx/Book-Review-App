package com.example.bookappreview.ui.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookappreview.repository.LivroRepository
import com.example.bookappreview.repository.UsuarioRepository
import com.example.bookappreview.ui.viewModel.AddLivroViewModel
import com.example.bookappreview.ui.viewModel.ReviewLivroViewModel

/**
 *  O factory cria o viewModel e coloca seus construtores com argumentos.
 */
class ReviewLivroViewModelFactory(
    private val repository: LivroRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ReviewLivroViewModel::class.java)) {
            ReviewLivroViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}