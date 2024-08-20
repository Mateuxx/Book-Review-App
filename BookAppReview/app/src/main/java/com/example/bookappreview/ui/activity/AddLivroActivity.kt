package com.example.bookappreview.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookappreview.R
import com.example.bookappreview.database.AppDatabase
import com.example.bookappreview.repository.MainRepository
import com.example.bookappreview.ui.viewModel.AddLivroViewModel
import com.example.bookappreview.ui.viewModel.UsuarioViewModel
import com.example.bookappreview.webclient.NetworkService
import kotlinx.coroutines.launch
import org.json.JSONObject

class AddLivroActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val repository = MainRepository(AppDatabase.instancia(this).userDao(), NetworkService())
        AddLivroViewModel(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_livro)

        viewModel.livros.observe(this) { livros ->
            // Atualizar a UI com a lista de livros
            Log.i("TAG", "Livros recebidos: ${livros.size}")
            println("Todos os livros: $livros")
        }

        viewModel.fetchBooks("harrypotter", this)
    }
}