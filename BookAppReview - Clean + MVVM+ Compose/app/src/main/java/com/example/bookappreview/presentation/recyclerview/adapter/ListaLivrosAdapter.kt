//package com.example.bookappreview.presentation.recyclerview.adapter
//
//import android.content.Context
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.bookappreview.databinding.LivroItemBinding
//import com.example.bookappreview.helpers.tentaCarregarImagem
//import com.example.bookappreview.presentation.model.LivroParcelable
//
//class ListaLivrosAdapter(
//    private val context: Context,
//    livroParcelables: List<LivroParcelable> = emptyList(),
//    var quandoClicaNoItem: (livroParcelable: LivroParcelable) -> Unit = {}
//) : RecyclerView.Adapter<ListaLivrosAdapter.ViewHolder>() {
//
//    private val livros = livroParcelables.toMutableList()
//
//    inner class ViewHolder(private val binding: LivroItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        private lateinit var livroParcelable: LivroParcelable
//
//        init {
//            itemView.setOnClickListener {
//                if (::livroParcelable.isInitialized) {
//                    quandoClicaNoItem(livroParcelable)
//                }
//            }
//        }
//
//        fun vincula(livroParcelable: LivroParcelable) {
//            this.livroParcelable = livroParcelable
//            binding.apply {
//                livroNome.text = livroParcelable.title
//                autorNome.text = livroParcelable.autor
//                anoPub.text = livroParcelable.year
//                //ver se a url foi requisitada
//            }
//
//            val visibilidade = if (livroParcelable.imagem != null) {
//                View.VISIBLE
//            } else {
//                View.GONE
//            }
//
//            binding.imageView.visibility = visibilidade
//
//            // Adicionando log para verificar a URL da imagem
//            Log.d("ListaLivrosAdapter", "URL da imagem: ${livroParcelable.imagem}")
//
//            binding.imageView.tentaCarregarImagem(livroParcelable.imagem)
//        }
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ListaLivrosAdapter.ViewHolder {
//        val inflater = LayoutInflater.from(context)
//        val binding = LivroItemBinding.inflate(inflater, parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ListaLivrosAdapter.ViewHolder, position: Int) {
//        val livro = livros[position]
//        holder.vincula(livro)
//    }
//
//    override fun getItemCount(): Int = livros.size
//
//    fun updateLivros(livroParcelables: List<LivroParcelable>) {
//        this.livros.clear()
//        this.livros.addAll(livroParcelables)
//        notifyDataSetChanged()
//    }
//
//}