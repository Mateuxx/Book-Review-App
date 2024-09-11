package com.example.bookappreview.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.bookappreview.model.LivroSalvo
import com.example.bookappreview.repository.LivroRepository

class ReviewLivroViewModel(
    private val repository: LivroRepository
) : ViewModel() {

    suspend fun saveBook(livroSalvo: LivroSalvo) {
        repository.save(livroSalvo)
    }
}