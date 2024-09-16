package com.example.bookappreview.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.bookappreview.data.model.Usuario
import com.example.bookappreview.domain.usecase.usuario.VerificaCadastroUseCase

class CadastroUsuarioViewModel(
    private val verficaCadastoUsuario: VerificaCadastroUseCase
) : ViewModel() {

    /**
     * Verifica se ja existe um usuario salvo no db
     * @return True caso exista
     */
    suspend fun verificaCadastraUsuario(usuario: Usuario, context: Context): Boolean {
        return verficaCadastoUsuario(usuario, context)
    }
}