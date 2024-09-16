package com.example.bookappreview.domain.usecase.usuario

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.bookappreview.data.model.Usuario
import com.example.bookappreview.data.repository.UsuarioRepositoryImpl

class VerificaCadastroUseCase (
    private val usuarioRepositoryImpl: UsuarioRepositoryImpl
){
    suspend operator fun invoke(usuario: Usuario, context: Context): Boolean {
        val buscaUsuario: Usuario? = usuarioRepositoryImpl.buscaPorUsername(usuario.username)
        buscaUsuario?.let { usuario ->
            Toast.makeText(
                context,
                "O nome de usuario ja existe",
                Toast.LENGTH_SHORT
            ).show()
            Log.i("TAG", "cadastraUsuario: O usuario ja existe!: $usuario")
            return false
        } ?: run {
            Log.i("TAG", "Esse usuário foi cadastrado: $usuario")
            usuarioRepositoryImpl.salva(usuario)
            return true
        }
    }
}