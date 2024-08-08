package com.example.bookappreview.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookappreview.database.dao.UserDao
import com.example.bookappreview.model.Usuario

@Database(
    entities = [Usuario::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {

        private var db: AppDatabase? = null
        fun instancia(context: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "book.db"
            ).build().also {
                return it
            }
        }
    }
}
