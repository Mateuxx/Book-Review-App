package com.example.bookappreview.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappreview.dao.UsuarioDao
import com.example.bookappreview.databinding.ActivityTelaLoginActivityBinding
import com.example.bookappreview.repository.helpers.EncapsulamentoMVVM
import com.example.bookappreview.repository.UsuarioRepository
import com.example.bookappreview.viewModel.UsuarioViewModel

class TelaLoginActivity : AppCompatActivity() {

//    // TODO: melhorar encapsulamento
//    private val viewModel by lazy {
//        val app = application as EncapsulamentoMVVM
//        UsuarioViewModel(app.usuarioRepository)
//    }

    private val viewModel: UsuarioViewModel by

    private val binding by lazy {
        ActivityTelaLoginActivityBinding.inflate(layoutInflater)
    }
    private val TAG = "LoginLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        login()
    }

    private fun login() {
        val btnlogin = binding.buttonLogin
        btnlogin.setOnClickListener {
            val campoUsuario = binding.usuario
            val usuarioLogin = campoUsuario.text.toString()
            // Mudar para log depois
            println("Valor do usuario : $usuarioLogin")
            val senhaText = binding.senha.text.toString()
            //Mudar para logasdas
            println("Valor do senha : $senhaText")
            viewModel.autenticacao(usuarioLogin, senhaText)
        }
    }
}