package com.example.bookappreview.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookappreview.data.model.LivroEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LivroSalvoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(livro: LivroEntity)

    @Query("SELECT * FROM livrosalvo ORDER BY id DESC LIMIT 5")
    fun getLatestBooks(): Flow<List<LivroEntity>>

}