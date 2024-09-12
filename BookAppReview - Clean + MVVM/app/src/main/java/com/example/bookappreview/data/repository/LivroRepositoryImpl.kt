package com.example.bookappreview.data.repository

import com.example.bookappreview.data.database.dao.LivroSalvoDao
import com.example.bookappreview.data.model.mapper.toEntity
import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.domain.repository.LivroRepository


/**
 * Repository data - tem acesso ao banco aos Data Source
 */
class LivroRepositoryImpl(
    private val livroSalvoDao: LivroSalvoDao
):LivroRepository {
    override suspend fun saveBooks(livro: Livro) {
        livroSalvoDao.salva(livro.toEntity())
    }
}

