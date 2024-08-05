package com.example.bookappreview.repository

import com.example.bookappreview.dao.UsuarioDao

class UsuarioRepository(
    private val dao: UsuarioDao
) {

    /**
     * Encapsulamento da autenticação la do DB.
     */
    fun autentica(nome: String, senha: String): Boolean =
         dao.searchByLoginAndSenha(nome, senha)
}