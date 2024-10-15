package com.example.bookappreview.domain.repository

import android.content.Context
import com.example.bookappreview.domain.model.Livro
import kotlinx.coroutines.flow.Flow

/**
 * independente da implementação do repo na camada data
 */
interface LivroRepository {

    /**
     * Save books in the database
     */
    suspend fun saveBooks(livro: Livro)

    /**
     * Busca os livros na API
     */
     fun fetchBooks(searchQuery: String, context: Context): Flow<List<Livro>>

    fun bookRecomendation(book: String): String

}