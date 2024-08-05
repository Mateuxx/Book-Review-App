package com.example.bookappreview.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.bookappreview.dao.UsuarioDao
import com.example.bookappreview.databinding.ActivityTelaLoginActivityBinding
import com.example.bookappreview.repository.UsuarioRepository
import com.example.bookappreview.viewModel.UsuarioViewModel
import com.example.bookappreview.viewModel.factory.UsuarioViewModelFactory

class TelaLoginActivity : AppCompatActivity() {

    private lateinit var viewModel: UsuarioViewModel

    //Dependencias para funcionar
    private val viewModelFactory = UsuarioViewModelFactory(
        UsuarioRepository(UsuarioDao()),
    )

    private val binding by lazy {
        ActivityTelaLoginActivityBinding.inflate(layoutInflater)
    }
    private val TAG = "LoginLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[UsuarioViewModel::class.java]
        login()
    }

    private fun login() {
        val btnlogin = binding.buttonLogin
        btnlogin.setOnClickListener {
            val campoUsuario = binding.usuario
            val usuarioLogin = campoUsuario.text.toString()
            val senhaText = binding.senha.text.toString()
            Log.i(TAG, "login: Usuario: $usuarioLogin - Senha: $senhaText")
            viewModel.autentica(usuarioLogin, senhaText)
        }
    }
}