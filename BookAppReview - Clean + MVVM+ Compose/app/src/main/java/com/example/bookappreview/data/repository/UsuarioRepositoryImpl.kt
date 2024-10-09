package com.example.bookappreview.data.repository

import com.example.bookappreview.data.database.dao.UserDao
import com.example.bookappreview.data.model.Usuario

class UsuarioRepositoryImpl(
    private val usuarioDao: UserDao,
) {
    /**
     * save the user on local db
     */
    suspend fun salva(usuario: Usuario) {
        usuarioDao.salvaUsuario(usuario)
    }

    /**
     * busca o usuario pelo Username no db local
     */
    suspend fun buscaPorUsername(username: String): Usuario? =
        usuarioDao.buscaUsername(username)

}