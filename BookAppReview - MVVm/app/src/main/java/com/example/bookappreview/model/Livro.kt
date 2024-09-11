package com.example.bookappreview.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID


@Parcelize
data class Livro(
    val id: String = UUID.randomUUID().toString(),
    var title: String?,
    var subtitle: String?,
    var publisher: String?,
    val imagem: String? = null, // url
    var description: String?,
    var pageCount: Int,
    var year: String?,
    var autor: String? = null,
    var genero: String
//    var authors: ArrayList<String>,
): Parcelable

