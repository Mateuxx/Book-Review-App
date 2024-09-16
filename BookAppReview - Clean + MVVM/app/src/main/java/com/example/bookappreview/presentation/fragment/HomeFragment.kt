package com.example.bookappreview.presentation.fragment

import HomeViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookappreview.R
import com.example.bookappreview.data.database.AppDatabase
import com.example.bookappreview.databinding.FragmentHomeBinding
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.data.repository.UsuarioRepositoryImpl
import com.example.bookappreview.presentation.recyclerview.adapter.ListaLivrosHomeAdapter
import com.example.bookappreview.presentation.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private val viewModel by lazy {
        val repository = UsuarioRepositoryImpl(
            AppDatabase.instancia(requireContext()).userDao(),
        )
        val factory = HomeViewModelFactory(repository)
        ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ListaLivrosHomeAdapter
    private val armazenaLivroParcelables = mutableListOf<LivroParcelable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListaLivrosHomeAdapter(requireContext())
        configuraRecyclerView()
        inicializaDadosPredefinidos()
        binding.searchBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addLivroFragment)
        }
    }

    private fun configuraRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView2.adapter = adapter

        adapter.quandoClicaNoItem = { livro ->
//            val intent = Intent(requireContext(), LivroDetalhesActivity::class.java).apply {
//                putExtra(CHAVE_LIVRO_OBj, livro)
//            }
//            startActivity(intent)
            val actions =
                HomeFragmentDirections.actionHomeFragmentToLivroDetalhesFragment2(livro)
            findNavController().navigate(actions)
        }
    }

    private fun inicializaDadosPredefinidos() {
        val query = "Harry Potter"
        viewModel.fetchBooks(query, requireContext())

        viewModel.livros.observe(viewLifecycleOwner) { livros ->
            armazenaLivroParcelables.clear()
            armazenaLivroParcelables.addAll(livros)
            adapter.updateLivros(armazenaLivroParcelables)
        }
    }
}

