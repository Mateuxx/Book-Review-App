package com.example.bookappreview.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookappreview.database.converter.Converters
import com.example.bookappreview.database.dao.LivroSalvoDao
import com.example.bookappreview.database.dao.UserDao
import com.example.bookappreview.model.LivroSalvo
import com.example.bookappreview.model.Usuario

@Database(
    entities = [Usuario::class,
        LivroSalvo::class
    ],
    version = 4,
    autoMigrations = [
        AutoMigration(from = 3, to = 4)
    ],
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun livroSalvodao(): LivroSalvoDao
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
