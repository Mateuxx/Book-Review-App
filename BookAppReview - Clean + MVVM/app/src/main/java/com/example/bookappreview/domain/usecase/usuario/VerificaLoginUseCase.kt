package com.example.bookappreview.domain.usecase.usuario

import android.util.Log
import com.example.bookappreview.data.repository.UsuarioRepositoryImpl

class VerificaLoginUseCase(
    private val usuarioRepositoryImpl: UsuarioRepositoryImpl
) {

    suspend operator fun invoke(username: String, senha: String): Boolean {
        val user = usuarioRepositoryImpl.buscaPorUsername(username)
        return try {
            if (username == user!!.username && user.senha == senha) {
                true
            } else {
                false
            }
        } catch (e: Exception) {
            Log.e("TAG", "verificaLogin: Usuario não colcado", e)
            false
        }
    }
}


