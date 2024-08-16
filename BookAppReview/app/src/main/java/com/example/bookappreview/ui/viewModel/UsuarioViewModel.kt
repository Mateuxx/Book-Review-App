package com.example.bookappreview.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bookappreview.model.Usuario
import com.example.bookappreview.repository.UsuarioRepository


class UsuarioViewModel(
    private val repository: UsuarioRepository
) :
    ViewModel() {
    private val TAG = "LogsViewModel"

    /*
    Verifica se o usuario e senha são passados corretamente
     */
    suspend fun verificaLogin(username: String, senha: String): Boolean {
        val user = repository.buscaPorUsername(username)
        if (username == user!!.username && user.senha == senha) {
            return true
        } else {
            return false
        }
    }

}


