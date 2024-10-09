package com.example.bookappreview.data.repository

import android.content.Context
import com.example.bookappreview.data.database.dao.LivroSalvoDao
import com.example.bookappreview.data.model.mapper.toEntity
import com.example.bookappreview.data.webclient.BookService
import com.example.bookappreview.data.webclient.aiservice.AiService
import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.domain.repository.LivroRepository


/**
 * Repository data - tem acesso ao banco aos Data Source
 */
class LivroRepositoryImpl(
    private val livroSalvoDao: LivroSalvoDao,
    private val bookService: BookService,
    private val bookRecomendation: AiService
    ) : LivroRepository {


    /**
     * save books on the local db
      */
    override suspend fun saveBooks(livro: Livro) {
        livroSalvoDao.salva(livro.toEntity())
    }

    /**
     * get books from the api
     */
    override suspend fun fetchBooks(searchQuery: String, context: Context): List<Livro> {
        return bookService.bookApi(searchQuery, context)
    }

    /**
     * Recommendation of similar books using AI
     */
    override fun bookRecomendation(book: String): String {
        TODO("Not yet implemented")
    }
}

