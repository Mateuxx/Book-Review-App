package com.example.bookappreview.domain.repository

import com.example.bookappreview.domain.model.Livro

/**
 * independente da implementação do repo na camada data
 */
interface LivroRepository {
    suspend fun saveBooks(livro: Livro)

}