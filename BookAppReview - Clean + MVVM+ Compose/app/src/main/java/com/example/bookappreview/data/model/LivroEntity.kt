package com.example.bookappreview.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "livrosalvo")
data class LivroEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L, // ID incremental gerado automaticamente
    val title: String?,
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