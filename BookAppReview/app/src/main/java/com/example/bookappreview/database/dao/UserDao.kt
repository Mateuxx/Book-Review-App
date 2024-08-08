package com.example.bookappreview.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.bookappreview.model.Usuario

@Dao
interface UserDao {
    @Insert
    suspend fun salvaUsuario(userDao: Usuario)
}