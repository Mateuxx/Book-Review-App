package com.example.bookappreview.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val nome: String,
    val email: String,
    val senha: String
)
