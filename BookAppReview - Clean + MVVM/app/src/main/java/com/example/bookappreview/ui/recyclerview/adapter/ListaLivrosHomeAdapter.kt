package com.example.bookappreview.ui.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookappreview.databinding.ItemListHorizontalBinding
import com.example.bookappreview.helpers.tentaCarregarImagem
import com.example.bookappreview.model.Livro

class ListaLivrosHomeAdapter(
    private val context: Context,
    livros: List<Livro> = emptyList(),
    var quandoClicaNoItem: (livro: Livro) -> Unit = {}
) : RecyclerView.Adapter<ListaLivrosHomeAdapter.ViewHolder>() {

    private val livros = livros.toMutableList()

    inner class ViewHolder(private val binding: ItemListHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var livro: Livro

        init {
            itemView.setOnClickListener {
                if (::livro.isInitialized) {
                    quandoClicaNoItem(livro)
                }
            }
        }

        fun vincula(livro: Livro) {
            this.livro = livro
            binding.apply {
                livroNome.text = livro.title
            }
            val visibilidade = if (livro.imagem != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = visibilidade

            binding.imageView.tentaCarregarImagem(livro.imagem)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemListHorizontalBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val livro = livros[position]
        holder.vincula(livro)
    }

    override fun getItemCount(): Int = livros.size

    fun updateLivros(livros: List<Livro>) {
        this.livros.clear()
        this.livros.addAll(livros)
        notifyDataSetChanged()
    }
}
