package com.example.bookappreview.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookappreview.data.database.AppDatabase
import com.example.bookappreview.databinding.FragmentAddLivroBinding
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.data.repository.UsuarioRepository
import com.example.bookappreview.presentation.recyclerview.adapter.ListaLivrosAdapter
import com.example.bookappreview.presentation.viewModel.AddLivroViewModel
import com.example.bookappreview.presentation.viewModel.factory.AddLivroViewModelFactory
import com.example.bookappreview.data.webclient.NetworkService

class AddLivroFragment : Fragment() {

    private val viewModel by lazy {
        val repository = UsuarioRepository(
            AppDatabase.instancia(requireContext()).userDao(),
            NetworkService()
        )
        val factory = AddLivroViewModelFactory(repository)
        ViewModelProvider(this, factory)[AddLivroViewModel::class.java]
    }

    // Lazy initialization of the binding
    private var _binding: FragmentAddLivroBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { ListaLivrosAdapter(requireContext()) }
    private val armazenaLivroParcelables = mutableListOf<LivroParcelable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddLivroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configuraRecyclerView(view)
        configuraSearchView()

        // Observe LiveData from ViewModel
        viewModel.livros.observe(viewLifecycleOwner) { livros ->
            adapter.updateLivros(livros)
            Log.i("TAG", "Livros recebidos: ${livros.size}")
            armazenaLivroParcelables.clear()
            armazenaLivroParcelables.addAll(livros)
        }
    }

    private fun configuraSearchView() {
        val searchView = binding.searchView


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
    }

    private fun fetchBooks(query: String) {
        viewModel.fetchBooks(query, requireContext())
    }

    private fun configuraRecyclerView(view: View) {
        val recyclerView = binding.activityListaProdutosRecyclerView
        recyclerView.adapter = adapter


        // Configurar a ação de clique nos itens
        adapter.quandoClicaNoItem = { livro ->
            val action =
                AddLivroFragmentDirections.actionAddLivroFragmentToLivroDetalhesFragment(livro)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evitar memory leaks
    }
}
