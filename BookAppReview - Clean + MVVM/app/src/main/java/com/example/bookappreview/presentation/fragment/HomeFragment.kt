package com.example.bookappreview.presentation.fragment

import HomeViewModelFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bookappreview.R
import com.example.bookappreview.data.webclient.aiservice.AiService
import com.example.bookappreview.databinding.FragmentHomeBinding
import com.example.bookappreview.di.Injection
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.presentation.recyclerview.adapter.ListaLivrosHomeAdapter
import com.example.bookappreview.presentation.recyclerview.adapter.ListaLivrosRecomendadosHomeAdapter
import com.example.bookappreview.presentation.viewModel.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        val buscarLivrosUseCase =
            Injection.provideBuscaLivrosUsecase(requireContext())
        val factory = HomeViewModelFactory(buscarLivrosUseCase)
        ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ListaLivrosHomeAdapter
    private lateinit var adapterRecomendados: ListaLivrosRecomendadosHomeAdapter
    private val armazenaLivroParcelables = mutableListOf<LivroParcelable>()
    private var armazenaLivrosRecomendados = mutableListOf<String>()
    private val teste = AiService()


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
        adapterRecomendados = ListaLivrosRecomendadosHomeAdapter(requireContext())
        configuraRecyclerView()
        inicializaDadosPredefinidos()
        lifecycleScope.launch {
            val bookList = recommendationBooks()
            Log.i("TAG", "Recomendações: $bookList")
            armazenaLivrosRecomendados = bookList.toMutableList()
        }


        binding.searchBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addLivroFragment)
        }
    }

    private suspend fun recommendationBooks(): List<String> {
        val result = teste.fetchBookRecommendations("Harry Potter") // Aguarda o resultado da requisição
        val aaa = teste.parseBookRecommendations(result).toMutableList() // Retorna a lista processada
        Log.i("TAG", "recommendationBooks: $aaa")
        return aaa
    }



    private fun configuraRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView2.adapter = adapter
        binding.recyclerViewRecommended.adapter = adapterRecomendados
        adapter.quandoClicaNoItem = { livro ->

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

