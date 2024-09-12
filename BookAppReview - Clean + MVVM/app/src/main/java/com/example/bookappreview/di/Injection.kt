package com.example.bookappreview.di

import android.content.Context
import com.example.bookappreview.data.database.AppDatabase
import com.example.bookappreview.data.repository.LivroRepositoryImpl
import com.example.bookappreview.data.webclient.NetworkService
import com.example.bookappreview.domain.usecase.livro.SalvarLivrosUsecase

object Injection {

    fun provideSalvarLivrosUsecase(context: Context): SalvarLivrosUsecase {
        val livroSalvoDao = AppDatabase.instancia(context).livroSalvodao()
        val networkService = NetworkService()

        val livroRepository = LivroRepositoryImpl(livroSalvoDao, networkService)

        return SalvarLivrosUsecase(livroRepository)
    }
}