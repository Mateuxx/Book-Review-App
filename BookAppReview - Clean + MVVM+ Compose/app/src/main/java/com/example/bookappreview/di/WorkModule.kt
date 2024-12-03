package com.example.bookappreview.di

import com.example.bookappreview.data.database.dao.LivroSalvoDao
import com.example.bookappreview.data.repository.LivroRepositoryImpl
import com.example.bookappreview.data.webclient.BookService
import com.example.bookappreview.data.webclient.aiservice.AiService
import com.example.bookappreview.domain.repository.LivroRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//object WorkModule {
//
//    @Provides
//    fun provideAiService(): AiService {
//        return AiService()
//    }
//}
