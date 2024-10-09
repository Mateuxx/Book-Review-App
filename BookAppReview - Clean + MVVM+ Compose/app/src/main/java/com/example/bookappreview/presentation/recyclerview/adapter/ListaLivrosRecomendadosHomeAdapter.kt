package com.example.bookappreview.presentation.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookappreview.databinding.ItemListHorizontalBinding
import com.example.bookappreview.helpers.tentaCarregarImagem
import com.example.bookappreview.presentation.model.LivroParcelable

class ListaLivrosRecomendadosHomeAdapter(
    private val context: Context,
    livroParcelables: List<LivroParcelable> = emptyList(),
    var quandoClicaNoItem: (livroParcelable: LivroParcelable) -> Unit = {}
) : RecyclerView.Adapter<ListaLivrosRecomendadosHomeAdapter.ViewHolder>() {

    private val livros = livroParcelables.toMutableList()

    inner class ViewHolder(private val binding: ItemListHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var livroParcelable: LivroParcelable

        init {
            itemView.setOnClickListener {
                if (::livroParcelable.isInitialized) {
                    quandoClicaNoItem(livroParcelable)
                }
            }
        }

        fun vincula(livroParcelable: LivroParcelable) {
            this.livroParcelable = livroParcelable
            binding.apply {
                Log.d("TAG", "vincula: Livro Recomendo titulo: ${livroParcelable.title}")
                livroNome.text = livroParcelable.title
            }
            binding.imageView.tentaCarregarImagem(livroParcelable.imagem)

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

    fun updateLivros(livroParcelables: List<LivroParcelable>) {
        this.livros.clear()
        this.livros.addAll(livroParcelables)
        notifyDataSetChanged()
    }
}
