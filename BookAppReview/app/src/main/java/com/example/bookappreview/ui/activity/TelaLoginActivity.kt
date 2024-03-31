package com.example.bookappreview.ui.activity

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bookappreview.R
import com.example.bookappreview.dao.UsuarioDao
import com.example.bookappreview.databinding.ActivityTelaLoginActivityBinding

class TelaLoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityTelaLoginActivityBinding.inflate(layoutInflater)
    }

    private val dao = UsuarioDao()
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
            val login = dao.searchByLoginAndSenha(usuarioLogin, senhaText)
            if (login) {
                Log.d(TAG, "")

            } else {
                Log.d(TAG, "Falha no login")
            }
        }
    }
}