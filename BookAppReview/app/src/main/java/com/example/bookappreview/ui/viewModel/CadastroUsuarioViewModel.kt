package com.example.bookappreview.ui.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.bookappreview.model.Usuario
import com.example.bookappreview.repository.UsuarioRepository
import java.security.AccessControlContext

class CadastroUsuarioViewModel(
    private val repository: UsuarioRepository
) : ViewModel() {

    /**
     * Verifica se ja existe um usuario salvo no db
     * @return True caso exista
     */
    suspend fun verificaCadastraUsuario(usuario: Usuario, context: Context): Boolean {
        val buscaUsuario: Usuario? = repository.buscaPorUsername(usuario.username)
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
            repository.salva(usuario)
            return true
        }
    }

}