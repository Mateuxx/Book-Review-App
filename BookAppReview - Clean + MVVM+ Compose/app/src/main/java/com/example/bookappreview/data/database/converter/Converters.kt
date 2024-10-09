package com.example.bookappreview.data.database.converter

import androidx.room.TypeConverter
import com.example.bookappreview.presentation.model.LivroParcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromLivro(livroParcelable: LivroParcelable): String {
        return Gson().toJson(livroParcelable)
    }

    @TypeConverter
    fun toLivro(livroString: String): LivroParcelable {
        val type = object : TypeToken<LivroParcelable>() {}.type
        return Gson().fromJson(livroString, type)
    }
}
