package com.example.bookappreview.presentation.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookappreview.data.repository.UsuarioRepositoryImpl
import com.example.bookappreview.domain.usecase.usuario.VerificaLoginUseCase
import com.example.bookappreview.presentation.viewModel.UsuarioViewModel

/**
 *  O factory cria o viewModel e coloca seus construtores com argumentos.
 */
class UsuarioViewModelFactory(
    private val verificaLoginUseCase: VerificaLoginUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
            UsuarioViewModel(this.verificaLoginUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}