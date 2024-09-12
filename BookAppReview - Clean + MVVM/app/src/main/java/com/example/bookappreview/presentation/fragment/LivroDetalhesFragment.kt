package com.example.bookappreview.presentation.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bookappreview.databinding.FragmentLivroDetalhesBinding
import com.example.bookappreview.helpers.tentaCarregarImagem
import com.example.bookappreview.presentation.model.LivroParcelable


class LivroDetalhesFragment : Fragment() {
    private var _binding: FragmentLivroDetalhesBinding? = null
    private val binding get() = _binding!!

    private var livroParcelableCarregado: LivroParcelable? = null

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

        livroParcelableCarregado = args.livro
        Log.i("TAG", "onViewCreated: livro carregado: $livroParcelableCarregado")

        livroParcelableCarregado?.let {
            preencherInfos(it)
        }

        binding.threeDots.setOnClickListener {
            livroParcelableCarregado?.let { livro ->
                val actions =
                    LivroDetalhesFragmentDirections.actionLivroDetalhesFragmentToReviewFragment(
                        livro
                    )
                findNavController().navigate(actions)
            }
        }
    }

    private fun preencherInfos(livroParcelable: LivroParcelable) {
        var isExpanded = false

        binding.apply {
            titulo.text = livroParcelable.title.toString()
            autor.text = livroParcelable.autor.toString()
            genero.text = livroParcelable.genero
            Log.i("TAG", "preencherInfos: Genero: ${livroParcelable.genero}")
            pages.text = livroParcelable.pageCount.toString()
            descricaoTexto.text = livroParcelable.description.toString()
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
            imagemLivro.tentaCarregarImagem(livroParcelable.imagem)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}