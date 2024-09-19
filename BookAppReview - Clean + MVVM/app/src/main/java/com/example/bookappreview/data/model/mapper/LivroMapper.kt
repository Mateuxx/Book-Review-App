package com.example.bookappreview.data.model.mapper

import com.example.bookappreview.data.model.LivroEntity
import com.example.bookappreview.domain.model.Livro

fun LivroEntity.toLivro(): Livro {
    return Livro(
    title,
    subtitle,
    publisher,
    imagem,
    description,
    pageCount,
    year,
    autor,
    genero,
    rated,
    like,
    review,
    dateReview
    )
}

fun Livro.toEntity(): LivroEntity {
    return LivroEntity(
        id = "",
        title,
        subtitle,
        publisher,
        imagem,
        description,
        pageCount,
        year,
        autor,
        genero,
        rated,
        like,
        review,
        dateReview
    )
}