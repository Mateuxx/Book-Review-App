package com.example.bookappreview.domain.repository

import android.content.Context
import com.example.bookappreview.domain.model.Livro

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
    suspend fun fetchBooks(searchQuery: String, context: Context): List<Livro>

}