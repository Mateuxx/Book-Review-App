package com.example.bookappreview.ui.activity.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bookappreview.databinding.FragmentLivroDetalhesBinding
import com.example.bookappreview.helpers.tentaCarregarImagem
import com.example.bookappreview.model.Livro


class LivroDetalhesFragment : Fragment() {
    private var _binding: FragmentLivroDetalhesBinding? = null
    private val binding get() = _binding!!

    private var livroCarregado: Livro? = null

    /**
     * passa os valores de livro clicado do fragment anterior
     */
    private val args by navArgs<LivroDetalhesFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLivroDetalhesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        livroCarregado = args.livro
        Log.i("TAG", "onViewCreated: livro carregado: $livroCarregado")

        livroCarregado?.let {
            preencherInfos(it)
        }

        binding.threeDots.setOnClickListener {
            livroCarregado?.let { livro ->
                val actions =
                    LivroDetalhesFragmentDirections.actionLivroDetalhesFragmentToReviewFragment(
                        livro
                    )
                findNavController().navigate(actions)
            }
        }
    }

    private fun preencherInfos(livro: Livro) {
        var isExpanded = false

        binding.apply {
            titulo.text = livro.title.toString()
            autor.text = livro.autor.toString()
            genero.text = livro.genero
            Log.i("TAG", "preencherInfos: Genero: ${livro.genero}")
            pages.text = livro.pageCount.toString()
            descricaoTexto.text = livro.description.toString()
            descricaoTexto.setOnClickListener {
                if (isExpanded) {
                    descricaoTexto.maxLines = 8
                    descricaoTexto.ellipsize = TextUtils.TruncateAt.END
                    isExpanded = false
                } else {
                    descricaoTexto.maxLines = Int.MAX_VALUE
                    descricaoTexto.ellipsize = null
                    isExpanded = true
                }
            }
            imagemLivro.tentaCarregarImagem(livro.imagem)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}