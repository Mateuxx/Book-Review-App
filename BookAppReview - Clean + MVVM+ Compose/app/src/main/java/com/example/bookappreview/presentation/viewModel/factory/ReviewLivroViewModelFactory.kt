package com.example.bookappreview.presentation.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookappreview.data.repository.LivroRepositoryImpl
import com.example.bookappreview.domain.usecase.livro.SalvarLivrosUsecase
import com.example.bookappreview.presentation.viewModel.ReviewLivroViewModel

/**
 *  O factory cria o viewModel e coloca seus construtores com argumentos.
 */
class ReviewLivroViewModelFactory(
    private val usecase: SalvarLivrosUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ReviewLivroViewModel::class.java)) {
            ReviewLivroViewModel(this.usecase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}