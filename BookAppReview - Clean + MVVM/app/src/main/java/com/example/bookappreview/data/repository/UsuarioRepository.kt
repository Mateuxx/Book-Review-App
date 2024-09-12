package com.example.bookappreview.data.repository

import android.content.Context
import com.example.bookappreview.data.database.dao.UserDao
import com.example.bookappreview.data.model.Usuario
import com.example.bookappreview.data.webclient.NetworkService
import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.presentation.model.LivroParcelable

class UsuarioRepository(
    private val usuarioDao: UserDao,
) {
    /**
     * save the user
     */
    suspend fun salva(usuario: Usuario) {
        usuarioDao.salvaUsuario(usuario)
    }

    /**
     * busca o usuario pelo Username na API
     */
    suspend fun buscaPorUsername(username: String): Usuario? =
        usuarioDao.buscaUsername(username)

}