package com.example.bookappreview.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.domain.usecase.livro.SalvarLivrosUsecase

class ReviewLivroViewModel(
   private val salvarLivrosUsecase: SalvarLivrosUsecase
) : ViewModel() {

    suspend fun saveBook(livro: Livro) {
        salvarLivrosUsecase(livro)
    }
}