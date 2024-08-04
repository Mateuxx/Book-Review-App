package com.example.bookappreview.repository

import com.example.bookappreview.dao.UsuarioDao
import com.example.bookappreview.model.Usuario

class UsuarioRepository(
    private val dao: UsuarioDao
) {

    /**
     * Encapsulamento da autenticação
     */
    fun autentica(nome: String, senha: String): Boolean =
         dao.searchByLoginAndSenha(nome, senha)
}