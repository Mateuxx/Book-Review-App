package com.example.bookappreview.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bookappreview.data.repository.UsuarioRepositoryImpl
import com.example.bookappreview.domain.usecase.usuario.VerificaLoginUseCase


class UsuarioViewModel(
    private val verificaLoginUseCase: VerificaLoginUseCase
) :
    ViewModel() {
    private val TAG = "LogsViewModel"

    /**
     * Verifica se o usuario e senha são passados corretamente
     */
    suspend fun verificaLogin(username: String, senha: String): Boolean {
        return verificaLoginUseCase(username, senha)
    }
}


