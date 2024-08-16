package com.example.bookappreview.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.bookappreview.database.AppDatabase
import com.example.bookappreview.databinding.ActivityTelaLoginActivityBinding
import com.example.bookappreview.helpers.vaiPara
import com.example.bookappreview.model.Usuario
import com.example.bookappreview.repository.UsuarioRepository
import com.example.bookappreview.ui.viewModel.UsuarioViewModel
import kotlinx.coroutines.launch

class TelaLoginActivity : AppCompatActivity() {

//    private lateinit var viewModel: UsuarioViewModel

    private val userDao by lazy {
        AppDatabase.instancia(this).userDao()
    }

    private val viewModel by lazy {
        val repository = UsuarioRepository(AppDatabase.instancia(this).userDao())
        UsuarioViewModel(repository)
    }

    private val binding by lazy {
        ActivityTelaLoginActivityBinding.inflate(layoutInflater)
    }
    private val TAG = "LoginLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val userTest = Usuario(
//            nome = "Gui",
//            username = "gui123",
//            email = "email",
//            senha = "123",
//        )
//        lifecycleScope.launch {
//            userDao.salvaUsuario(userTest)
//        }
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
                if (viewModel.verificaLogin(usuarioLogin, senhaText)) {
                    vaiPara(HomeActivity::class.java)
                }
            }

        }
    }

}