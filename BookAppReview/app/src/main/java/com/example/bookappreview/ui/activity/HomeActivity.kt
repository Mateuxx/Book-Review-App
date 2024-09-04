package com.example.bookappreview.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappreview.database.AppDatabase
import com.example.bookappreview.databinding.ActivityHomeBinding
import com.example.bookappreview.model.Livro
import com.example.bookappreview.repository.UsuarioRepository
import com.example.bookappreview.ui.recyclerview.adapter.ListaLivrosHomeAdapter
import com.example.bookappreview.ui.viewModel.HomeViewModel
import com.example.bookappreview.webclient.NetworkService

class HomeActivity : AppCompatActivity() {

    // Inicializa o ViewModel com o repositório
    private val viewModel by lazy {
        val repository = UsuarioRepository(AppDatabase.instancia(this).userDao(), NetworkService())
        HomeViewModel(repository)
    }

    // Inicializa o binding para o layout da Activity
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    // Configura o adapter para a RecyclerView
    private val adapter = ListaLivrosHomeAdapter(context = this)

    // Lista para armazenar os livros
    private val armazenaLivros = mutableListOf<Livro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        inicializaDadosPredefinidos()
    }

    private fun configuraRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView2.adapter = adapter

        adapter.quandoClicaNoItem = { livro ->
            val intent = Intent(
                this,
                LivroDetalhesActivity::class.java
            ).apply {
                putExtra(CHAVE_LIVRO_OBj, livro)
            }
            startActivity(intent)
        }
    }

    private fun inicializaDadosPredefinidos() {
        val query = "Harry Potter"
        viewModel.fetchBooks(query, this)

        // Observa os dados retornados pelo ViewModel e atualiza o adapter
        viewModel.livros.observe(this) { livros ->
            // Atualiza o adapter com a lista de livros obtida
            armazenaLivros.clear()
            armazenaLivros.addAll(livros)
            adapter.updateLivros(armazenaLivros)
            Log.i("TAG", "Livros recebidos: ${livros.size}")
        }
    }
}
