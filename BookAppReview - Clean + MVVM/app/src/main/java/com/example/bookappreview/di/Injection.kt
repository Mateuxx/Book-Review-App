package com.example.bookappreview.di

import android.content.Context
import com.example.bookappreview.data.database.AppDatabase
import com.example.bookappreview.data.repository.LivroRepositoryImpl
import com.example.bookappreview.domain.usecase.livro.SalvarLivrosUsecase

object Injection {

    fun provideSalvarLivrosUsecase(context: Context): SalvarLivrosUsecase {
        // Obtenha a instância do LivroSalvoDao a partir do AppDatabase
        val livroSalvoDao = AppDatabase.instancia(context).livroSalvodao()

        // Crie a instância do LivroRepositoryImpl com o LivroSalvoDao
        val livroRepository = LivroRepositoryImpl(livroSalvoDao)

        // Retorne a instância de SalvarLivrosUsecase
        return SalvarLivrosUsecase(livroRepository)
    }
}