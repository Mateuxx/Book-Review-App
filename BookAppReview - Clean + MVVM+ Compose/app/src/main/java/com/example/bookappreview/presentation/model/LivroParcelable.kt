package com.example.bookappreview.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID


/**
 * Apenas para passar o tipo livro entre as camadas de ui
 */
@Parcelize
data class LivroParcelable(
    val id: String = UUID.randomUUID().toString(),
    var title: String?,
    var subtitle: String?,
    var publisher: String?,
    val imagem: String? = null, // URL
    var description: String?,
    var pageCount: Int,
    var year: String?,
    var autor: String? = null,
    var genero: String
): Parcelable

