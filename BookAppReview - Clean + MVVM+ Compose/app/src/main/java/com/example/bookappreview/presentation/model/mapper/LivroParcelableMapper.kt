package com.example.bookappreview.presentation.model.mapper

import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.presentation.model.LivroParcelable

//Pega uma lista de Livros e converte para uma lista de livros parcelable
fun List<Livro>.toParcelableList(): List<LivroParcelable> {
    return this.map { it.toParcelable() }
}

// Função de extensão para converter um Livro em LivroParcelable
fun Livro.toParcelable(): LivroParcelable {
    return LivroParcelable(
        title = this.title,
        subtitle = this.subtitle,
        publisher = this.publisher,
        imagem = this.imagem,
        description = this.description,
        pageCount = this.pageCount,
        year = this.year,
        autor = this.autor,
        genero = this.genero
    )
}
