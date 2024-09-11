package com.example.bookappreview.ui.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookappreview.repository.UsuarioRepository
import com.example.bookappreview.ui.viewModel.UsuarioViewModel

/**
 *  O factory cria o viewModel e coloca seus construtores com argumentos.
 */
class UsuarioViewModelFactory(
    private val repository: UsuarioRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
            UsuarioViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}