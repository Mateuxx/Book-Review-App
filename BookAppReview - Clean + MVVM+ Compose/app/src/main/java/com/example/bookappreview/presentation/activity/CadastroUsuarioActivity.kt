package com.example.bookappreview.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bookappreview.data.database.AppDatabase
import com.example.bookappreview.databinding.ActivityCadastroUsuarioBinding
import com.example.bookappreview.helpers.vaiPara
import com.example.bookappreview.data.model.Usuario
import com.example.bookappreview.data.repository.UsuarioRepositoryImpl
import com.example.bookappreview.di.Injection
import com.example.bookappreview.presentation.viewModel.AddLivroViewModel
import com.example.bookappreview.presentation.viewModel.CadastroUsuarioViewModel
import com.example.bookappreview.presentation.viewModel.ReviewLivroViewModel
import com.example.bookappreview.presentation.viewModel.factory.AddLivroViewModelFactory
import com.example.bookappreview.presentation.viewModel.factory.CadastroUsuarioViewModelFactory
import com.example.bookappreview.presentation.viewModel.factory.ReviewLivroViewModelFactory
import kotlinx.coroutines.launch

class CadastroUsuarioActivity : AppCompatActivity() {

    private val viewModel: CadastroUsuarioViewModel by lazy {
        val cadastrLivroUseCase =
            Injection.provideVerificaCadastroUseCase(this)
        val factory = CadastroUsuarioViewModelFactory(cadastrLivroUseCase)
        ViewModelProvider(this, factory)[CadastroUsuarioViewModel::class.java]
    }

    private val binding by lazy {
        ActivityCadastroUsuarioBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lifecycleScope.launch {
            cadastra()
        }
    }

    private suspend fun cadastra() {
        val btnCriarConta = binding.buttonCriarConta
        btnCriarConta.setOnClickListener {
            val user = criaUsuario()
            Log.i("TAG", "cadastra: $user")
            lifecycleScope.launch {
                if (viewModel.verificaCadastraUsuario(user, this@CadastroUsuarioActivity)) {
                    vaiPara(TelaLoginActivity::class.java)
                }
            }
        }
    }

    private fun criaUsuario(): Usuario {
        val nome = binding.nomeCadastro.text.toString()
        val email = binding.senhaCadastro.text.toString()
        val username = binding.usernameCadastro.text.toString()
        val senha = binding.senhaCadastro.text.toString()

        Log.i("TAG", "criaUsuario: $nome")

        return Usuario(
            nome = nome,
            username = username,
            email = email,
            senha = senha
        )
    }
}