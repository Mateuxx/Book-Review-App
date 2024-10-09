package com.example.bookappreview.presentation.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookappreview.domain.usecase.usuario.VerificaCadastroUseCase
import com.example.bookappreview.presentation.viewModel.CadastroUsuarioViewModel

/**
 *  O factory cria o viewModel e coloca seus construtores com argumentos.
 */
class CadastroUsuarioViewModelFactory(
    private val usecase: VerificaCadastroUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CadastroUsuarioViewModel::class.java)) {
            CadastroUsuarioViewModel(this.usecase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}