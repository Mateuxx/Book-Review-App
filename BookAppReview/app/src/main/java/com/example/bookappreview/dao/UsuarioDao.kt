package com.example.bookappreview.dao

import android.util.Log
import com.example.bookappreview.model.Usuario

class UsuarioDao {


    fun insert(usuario: Usuario) =
        usuarios.add(usuario)

    fun searchAll(): List<Usuario> =
        usuarios.toList()

    fun searchByLoginAndSenha(nome: String, senha: String): Boolean {
         val result = usuarios.any{
            it.nome == nome && it.senha == senha
        }
        return result
    }
    companion object {
        private val usuarios = mutableListOf<Usuario>(
            Usuario(
                nome = "admin",
                email = "guilherme@gmail.com",
                senha = "123"
            )
        )
    }
}