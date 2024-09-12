package com.example.bookappreview.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "livrosalvo")
data class LivroEntity (
    @PrimaryKey
    @ColumnInfo(defaultValue = "")
    val id: String,
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