package com.example.bookappreview.domain.model

import java.util.UUID


/**
 *  A classe mais pura do objeto, não depende de nenhuma lib (android ou externa)
 *  ou de qualquer framework
 */
data class Livro(
    var title: String?,
    val subtitle: String?,
    val publisher: String?,
    val imagem: String?,
    val description: String?,
    val pageCount: Int,
    val year: String?,
    val autor: String?,
    val genero: String,
    var rated: Int,
    var like: Boolean,
    var review: String,
    var dateReview: String

)
