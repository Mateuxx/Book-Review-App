package com.example.bookappreview.ui.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookappreview.databinding.LivroItemBinding
import com.example.bookappreview.helpers.tentaCarregarImagem
import com.example.bookappreview.model.Livro

class ListaLivrosAdapter(
    private val context: Context,
    livros: List<Livro> = emptyList(),
    var quandoClicaNoItem: (livro: Livro) -> Unit = {}
) : RecyclerView.Adapter<ListaLivrosAdapter.ViewHolder>() {

    private val livros = livros.toMutableList()

    inner class ViewHolder(private val binding: LivroItemBinding) :
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
                autorNome.text = livro.autor
                anoPub.text = livro.year
                //ver se a url foi requisitada
            }

            val visibilidade = if(livro.imagem != null) {
                View.VISIBLE
            }else {
                View.GONE
            }

            binding.imageView.visibility = visibilidade

            // Adicionando log para verificar a URL da imagem
            Log.d("ListaLivrosAdapter", "URL da imagem: ${livro.imagem}")

            binding.imageView.tentaCarregarImagem(livro.imagem)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListaLivrosAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = LivroItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListaLivrosAdapter.ViewHolder, position: Int) {
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