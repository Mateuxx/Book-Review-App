package com.example.bookappreview.repository

import com.example.bookappreview.database.dao.UserDao
import com.example.bookappreview.model.Usuario

class UsuarioRepository(
    private val dao: UserDao
) {
    /**
     * save the user
     */
    suspend fun salva(usuario: Usuario) {
        dao.salvaUsuario(usuario)
    }

    /**
     * busca o usuario pelo Username
     */
    suspend fun buscaPorUsername(username: String): Usuario? =
        dao.buscaUsername(username)
}