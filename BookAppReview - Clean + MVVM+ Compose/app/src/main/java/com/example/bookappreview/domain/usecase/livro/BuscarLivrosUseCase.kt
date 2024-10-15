package com.example.bookappreview.domain.usecase.livro

import android.content.Context
import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.domain.repository.LivroRepository
import kotlinx.coroutines.flow.Flow

class BuscarLivrosUseCase(
    private val livroRepo: LivroRepository
) {
    /**
     * Chama o repositório para buscar livros e retorna um Flow de Livros
     */
    operator fun invoke(books: String, context: Context): Flow<List<Livro>> {
        return livroRepo.fetchBooks(books, context)
    }
}