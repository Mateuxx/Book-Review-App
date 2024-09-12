package com.example.bookappreview.presentation.model.mapper

import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.presentation.model.LivroParcelable

fun LivroParcelable.toDomain(): Livro {
    return Livro(
        id,
        title,
        subtitle,
        publisher,
        imagem,
        description,
        pageCount,
        year,
        autor,
        genero,
        rated = 0,
        like = false,
        review = "",
        dateReview = ""
    )
}