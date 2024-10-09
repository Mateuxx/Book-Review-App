package com.example.bookappreview.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bookappreview.data.model.Usuario

@Dao
interface UserDao {
    @Insert
    suspend fun salvaUsuario(userDao: Usuario)

    @Query("SELECT * FROM usuario WHERE username = :username")
    suspend fun buscaUsername(username: String): Usuario?

}