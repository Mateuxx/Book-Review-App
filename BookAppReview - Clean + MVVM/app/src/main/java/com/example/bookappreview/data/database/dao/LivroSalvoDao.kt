package com.example.bookappreview.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.bookappreview.data.model.LivroEntity

@Dao
interface LivroSalvoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(livro: LivroEntity)
}