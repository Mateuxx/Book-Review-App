package com.example.bookappreview.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.bookappreview.database.dao.UserDao
import com.example.bookappreview.model.Livro
import com.example.bookappreview.model.Usuario
import com.example.bookappreview.webclient.NetworkService

class MainRepository(
    private val dao: UserDao,
    private val networkService: NetworkService
) {
    /**
     * save the user
     */
    suspend fun salva(usuario: Usuario) {
        dao.salvaUsuario(usuario)
    }

    /**
     * busca o usuario pelo Username na API
     */
    suspend fun buscaPorUsername(username: String): Usuario? =
        dao.buscaUsername(username)

    /**
     * Busca os livros
     */
    suspend fun fetchBooks(searchQuery: String, context: Context): List<Livro> {
        return networkService.bookApi(searchQuery, context)
    }

}