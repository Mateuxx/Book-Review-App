package com.example.bookappreview.data.repository

import android.content.Context
import com.example.bookappreview.data.database.dao.LivroSalvoDao
import com.example.bookappreview.data.model.mapper.toEntity
import com.example.bookappreview.data.webclient.NetworkService
import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.domain.repository.LivroRepository


/**
 * Repository data - tem acesso ao banco aos Data Source
 */
class LivroRepositoryImpl(
    private val livroSalvoDao: LivroSalvoDao,
    private val networkService: NetworkService

) : LivroRepository {
    override suspend fun saveBooks(livro: Livro) {
        livroSalvoDao.salva(livro.toEntity())
    }

    /**
     * get books from the api
     */
    override suspend fun fetchBooks(searchQuery: String, context: Context): List<Livro> {
        return networkService.bookApi(searchQuery, context)
    }
}

