package com.example.bookappreview.domain.usecase.livro

import com.example.bookappreview.domain.repository.LivroRepository

class RecomendaLivrosUseCase(
    private val repository: LivroRepository
) {
     operator fun invoke(book: String) {
        repository.bookRecomendation(book)
    }

}