package com.example.bookappreview.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bookappreview.data.database.AppDatabase
import com.example.bookappreview.databinding.ActivityTelaLoginActivityBinding
import com.example.bookappreview.helpers.vaiPara
import com.example.bookappreview.data.repository.UsuarioRepositoryImpl
import com.example.bookappreview.presentation.viewModel.UsuarioViewModel
import com.example.bookappreview.data.webclient.NetworkService
import com.example.bookappreview.di.Injection
import com.example.bookappreview.presentation.viewModel.CadastroUsuarioViewModel
import com.example.bookappreview.presentation.viewModel.factory.CadastroUsuarioViewModelFactory
import com.example.bookappreview.presentation.viewModel.factory.UsuarioViewModelFactory
import kotlinx.coroutines.launch

class TelaLoginActivity : AppCompatActivity() {

//    private lateinit var viewModel: UsuarioViewModel

    private val userDao by lazy {
        AppDatabase.instancia(this).userDao()
    }

    private val viewModel:UsuarioViewModel by lazy {
        val verificaLoginUseCase =
            Injection.provideVerificaLoginUseCase(this)
        val factory = UsuarioViewModelFactory(verificaLoginUseCase)
        ViewModelProvider(this, factory)[UsuarioViewModel::class.java]
    }

    private val binding by lazy {
        ActivityTelaLoginActivityBinding.inflate(layoutInflater)
    }
    private val TAG = "LoginLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        login()
        cadastrar()
    }

    private fun cadastrar() {
        val btnCadastro = binding.buttonCadastro
        btnCadastro.setOnClickListener {
            vaiPara(CadastroUsuarioActivity::class.java)
        }
    }

    private fun login() {
        val btnlogin = binding.buttonLogin
        btnlogin.setOnClickListener {
            val campoUsuario = binding.usuario
            val usuarioLogin = campoUsuario.text.toString()
            val senhaText = binding.senha.text.toString()
            Log.i(TAG, "login: Usuario: $usuarioLogin - Senha: $senhaText")

            lifecycleScope.launch {
//                if (viewModel.verificaLogin(usuarioLogin, senhaText)) {
//                    vaiPara(HomeActivity::class.java)
//                } else {
//                    Toast.makeText(
//                        this@TelaLoginActivity,
//                        "Coloque um usuario válido",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
            }

        }
    }

}