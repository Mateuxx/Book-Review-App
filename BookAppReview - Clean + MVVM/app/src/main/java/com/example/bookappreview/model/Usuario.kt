package com.example.bookappreview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val nome: String,
    @ColumnInfo(defaultValue = "")
    val username: String,
    val email: String,
    val senha: String
)
