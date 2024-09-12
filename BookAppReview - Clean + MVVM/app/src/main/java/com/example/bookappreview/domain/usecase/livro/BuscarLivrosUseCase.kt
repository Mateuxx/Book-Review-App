package com.example.bookappreview.domain.usecase.livro

import android.content.Context
import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.domain.repository.LivroRepository

class BuscarLivrosUseCase(
    private val livroRepo: LivroRepository
) {
    /**
     * Invoke eh uma convensão dentro de usecase para chamarmos de forma mais limpa a classe
     * diretamente pelo metodo sem a necessidade de fazer o instanciamento do objeto
     */
    suspend operator fun invoke(books: String,context: Context): List<Livro> {
        return livroRepo.fetchBooks(books, context)
    }
}