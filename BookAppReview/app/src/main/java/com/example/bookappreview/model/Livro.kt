package com.example.bookappreview.model

data class Livro(
    var title: String,
    var subtitle: String,
    var publisher: String,
    val imagem: String? = null, // url
    var description: String,
    var pageCount: Int,
    var year: String,
    var autor: String? = null

//    var authors: ArrayList<String>,
)
