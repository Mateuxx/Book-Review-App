package com.example.bookappreview.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.bookappreview.R
import com.example.bookappreview.database.AppDatabase
import com.example.bookappreview.databinding.ActivityAddLivroBinding
import com.example.bookappreview.model.Livro
import com.example.bookappreview.repository.MainRepository
import com.example.bookappreview.ui.viewModel.AddLivroViewModel
import com.example.bookappreview.webclient.NetworkService

class AddLivroActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val repository = MainRepository(AppDatabase.instancia(this).userDao(), NetworkService())
        AddLivroViewModel(repository)
    }

    private val binding by lazy {
        ActivityAddLivroBinding.inflate(layoutInflater)
    }

    private val bindingLivro by lazy {

    }

    private lateinit var searchView: SearchView

    private val armazenaLivros = mutableListOf<Livro>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        searchView = binding.searchView


        // Configurar a SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        viewModel.livros.observe(this) { livros ->
            // Atualizar a UI com a lista de livros
            Log.i("TAG", "Livros recebidos: ${livros.size}")
            println("Todos os livros: $livros")
            armazenaLivros.addAll(livros)
        }

        viewModel.fetchBooks("harrypotter", this)

        Log.i("TAG", "onCreate: Salvou os livros: $armazenaLivros")
    }

}