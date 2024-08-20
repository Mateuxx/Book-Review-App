package com.example.bookappreview.model

import java.util.ArrayList

data class Livro(
    var title: String,
    var subtitle: String,
    var authors: ArrayList<String>,
    var publisher: String,
    var thumbnail: String, // url
    var description: String,
    var pageCount: Int

)
