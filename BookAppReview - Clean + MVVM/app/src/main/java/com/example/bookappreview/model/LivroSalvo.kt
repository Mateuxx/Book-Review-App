package com.example.bookappreview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "livrosalvo")
data class LivroSalvo (
    @PrimaryKey
    @ColumnInfo(defaultValue = "")
    val id: String,
    val livro: Livro,
    var rated: Int,
    var like: Boolean,
    var review: String,
    var dateReview: String

)