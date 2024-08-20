package com.example.bookappreview.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bookappreview.repository.MainRepository


class UsuarioViewModel(
    private val repository: MainRepository
) :
    ViewModel() {
    private val TAG = "LogsViewModel"

    /**
     * Verifica se o usuario e senha são passados corretamente
     */
    suspend fun verificaLogin(username: String, senha: String): Boolean {
        val user = repository.buscaPorUsername(username)
        return try {
            if (username == user!!.username && user.senha == senha) {
                true
            } else {
                false
            }
        }catch (e: Exception) {
            Log.e(TAG, "verificaLogin: Usuario não colcado",e )
            false
        }
    }

}


