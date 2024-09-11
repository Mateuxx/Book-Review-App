package com.example.bookappreview.repository

import com.example.bookappreview.database.dao.LivroSalvoDao
import com.example.bookappreview.model.LivroSalvo

class LivroRepository(
    private val dao: LivroSalvoDao
) {
    /**
     * Saves the books in the database
     */
    suspend fun save(livro:LivroSalvo) {
        dao.salva(livro)
    }
}