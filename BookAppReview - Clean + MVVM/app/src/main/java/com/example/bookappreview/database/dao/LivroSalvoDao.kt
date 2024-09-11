package com.example.bookappreview.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.bookappreview.model.LivroSalvo

@Dao
interface LivroSalvoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(livro: LivroSalvo)
}