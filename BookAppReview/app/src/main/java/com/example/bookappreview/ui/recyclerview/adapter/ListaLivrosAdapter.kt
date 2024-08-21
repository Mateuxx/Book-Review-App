package com.example.bookappreview.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookappreview.databinding.LivroItemBinding
import com.example.bookappreview.model.Livro

class ListaLivrosAdapter (
    private val context: Context,
    livros: List<Livro> = emptyList(),
    var quandoClicaNoItem: (livro: Livro) -> Unit = {}
): RecyclerView.Adapter<ListaLivrosAdapter.ViewHolder>(){

    private val livros = livros.toMutableList()
    inner class ViewHolder(private val binding: LivroItemBinding):
    RecyclerView.ViewHolder(binding.root){
        private lateinit var livro: Livro

        init {
            itemView.setOnClickListener{
                if (::livro.isInitialized){
                    quandoClicaNoItem(livro)
                }
            }
        }

        fun vincula(livro:Livro) {
            this.livro = livro
            binding.apply {
                livroNome.text = livro.title
                autorNome.text = "Pedro Alvares cabral"
                anoPub.text = livro.pageCount.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaLivrosAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = LivroItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListaLivrosAdapter.ViewHolder, position: Int) {
        val livro = livros[position]
        holder.vincula(livro)
    }

    override fun getItemCount(): Int = livros.size

    fun updateLivros( livros: List<Livro>) {
        this.livros.clear()
        this.livros.addAll(livros)
        notifyDataSetChanged()
    }

}