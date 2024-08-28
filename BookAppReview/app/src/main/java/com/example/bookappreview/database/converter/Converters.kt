package com.example.bookappreview.database.converter

import androidx.room.TypeConverter
import com.example.bookappreview.model.Livro
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromLivro(livro: Livro): String {
        return Gson().toJson(livro)
    }

    @TypeConverter
    fun toLivro(livroString: String): Livro {
        val type = object : TypeToken<Livro>() {}.type
        return Gson().fromJson(livroString, type)
    }
}
