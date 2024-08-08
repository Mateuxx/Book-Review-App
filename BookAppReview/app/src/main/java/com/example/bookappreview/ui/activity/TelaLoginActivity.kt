package com.example.bookappreview.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bookappreview.database.AppDatabase
import com.example.bookappreview.databinding.ActivityTelaLoginActivityBinding
import com.example.bookappreview.model.Usuario
import kotlinx.coroutines.launch

class TelaLoginActivity : AppCompatActivity() {

//    private lateinit var viewModel: UsuarioViewModel

    private val userDao by lazy {
        AppDatabase.instancia(this).userDao()
    }

    private val binding by lazy {
        ActivityTelaLoginActivityBinding.inflate(layoutInflater)
    }
    private val TAG = "LoginLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val userTest = Usuario(
            nome = "Mateus",
            email = "email",
            senha = "senha"
        )

        lifecycleScope.launch {
            userDao.salvaUsuario(userTest)
        }

        login()
    }

    private fun login() {
        val btnlogin = binding.buttonLogin
        btnlogin.setOnClickListener {
            val campoUsuario = binding.usuario
            val usuarioLogin = campoUsuario.text.toString()
            val senhaText = binding.senha.text.toString()
            Log.i(TAG, "login: Usuario: $usuarioLogin - Senha: $senhaText")
        }
    }
}