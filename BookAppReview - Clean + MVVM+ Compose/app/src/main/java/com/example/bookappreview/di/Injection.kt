package com.example.bookappreview.di

import android.content.Context
import com.example.bookappreview.data.database.AppDatabase
import com.example.bookappreview.data.repository.LivroRepositoryImpl
import com.example.bookappreview.data.repository.UsuarioRepositoryImpl
import com.example.bookappreview.data.webclient.BookService
import com.example.bookappreview.data.webclient.aiservice.AiService
import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
import com.example.bookappreview.domain.usecase.livro.SalvarLivrosUsecase
import com.example.bookappreview.domain.usecase.usuario.VerificaCadastroUseCase
import com.example.bookappreview.domain.usecase.usuario.VerificaLoginUseCase

object Injection {

    fun provideSalvarLivrosUsecase(context: Context): SalvarLivrosUsecase {
        val livroSalvoDao = AppDatabase.instancia(context).livroSalvodao()
        val bookService = BookService()
        val recomendationService = AiService()

        val livroRepository = LivroRepositoryImpl(livroSalvoDao, bookService, recomendationService)

        return SalvarLivrosUsecase(livroRepository)
    }

    fun provideBuscaLivrosUsecase(context: Context): BuscarLivrosUseCase {
        val livroSalvoDao = AppDatabase.instancia(context).livroSalvodao()
        val bookService = BookService()

        val recomendationService = AiService()

        val livroRepository = LivroRepositoryImpl(livroSalvoDao, bookService, recomendationService)

        return BuscarLivrosUseCase(livroRepository)
    }

    fun provideVerificaLoginUseCase(context: Context): VerificaLoginUseCase {
        val usuarioDao = AppDatabase.instancia(context).userDao()

        val usuarioRepository = UsuarioRepositoryImpl(usuarioDao)

        return VerificaLoginUseCase(usuarioRepository)
    }

    fun provideVerificaCadastroUseCase(context: Context): VerificaCadastroUseCase {
        val usuarioDao = AppDatabase.instancia(context).userDao()

        val usuarioRepository = UsuarioRepositoryImpl(usuarioDao)

        return VerificaCadastroUseCase(usuarioRepository)
    }
}