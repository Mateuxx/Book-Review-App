package com.example.bookappreview.viewModel

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.example.bookappreview.R
import com.example.bookappreview.repository.UsuarioRepository

class UsuarioViewModel(
    private val repository: UsuarioRepository
):ViewModel(){

    private val TAG = "UsuarioViewModel"

    /**
     * Autenticaçao feita com sucesso!
     */
    fun autentica(nome: String, senha: String){
        val result = repository.autentica(nome, senha)
        if (result){
            Log.d(TAG, "autentica: Autenticação deu certo")
        }else {
            Log.e(TAG, "autentica: Falha na autenticação", )
        }
    }

}
