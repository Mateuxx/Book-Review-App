package com.example.bookappreview.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookappreview.data.database.converter.Converters
import com.example.bookappreview.data.database.dao.LivroSalvoDao
import com.example.bookappreview.data.database.dao.UserDao
import com.example.bookappreview.data.model.LivroEntity
import com.example.bookappreview.data.model.Usuario

@Database(
    entities = [Usuario::class,
        LivroEntity::class
    ],
    version = 1,
//    autoMigrations = [
//        AutoMigration(from = 3, to = 4)
//    ],
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
