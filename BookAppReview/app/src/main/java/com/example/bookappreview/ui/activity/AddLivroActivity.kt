package com.example.bookappreview.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.bookappreview.database.AppDatabase
import com.example.bookappreview.databinding.ActivityAddLivroBinding
import com.example.bookappreview.model.Livro
import com.example.bookappreview.repository.UsuarioRepository
import com.example.bookappreview.ui.recyclerview.adapter.ListaLivrosAdapter
import com.example.bookappreview.ui.viewModel.AddLivroViewModel
import com.example.bookappreview.webclient.NetworkService

class AddLivroActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val repository = UsuarioRepository(AppDatabase.instancia(this).userDao(), NetworkService())
        AddLivroViewModel(repository)
    }

    private val binding by lazy {
        ActivityAddLivroBinding.inflate(layoutInflater)
    }

    private val adapter = ListaLivrosAdapter(context = this)

    private lateinit var searchView: SearchView

    private val armazenaLivros = mutableListOf<Livro>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()


        searchView = binding.searchView

        // Configurar a SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { fetchBooks(it) }
                return true
            }
        })

        viewModel.livros.observe(this) { livros ->
            // Atualizar a UI com a lista de livros
            adapter.updateLivros(livros)
            Log.i("TAG", "Livros recebidos: ${livros.size}")
            for (i in livros.indices) {
                println(livros[i])
            }
            armazenaLivros.addAll(livros)
        }
    }

    private fun fetchBooks(query: String) {
        viewModel.fetchBooks(query, this)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutosRecyclerView
        recyclerView.adapter = adapter
        /**
         * Define que ao clicar em um item do recycler ele ir para a pagina desse livro
         * passando um chave pela intent para conseguirmos receber os dados desse livro nessa
         * nova activity por meio de seu ID
         */
        adapter.quandoClicaNoItem = { livro ->
            val intent = Intent(
                this,
                LivroDetalhesActivity::class.java
            ).apply {
                Log.i("TAG", "configuraRecyclerView: livro ID: ${livro}")
                putExtra(CHAVE_LIVRO_OBj, livro)
            }
            startActivity(intent)//inicia a activty recebendo a intent no qual foi passada

        }

    }
}