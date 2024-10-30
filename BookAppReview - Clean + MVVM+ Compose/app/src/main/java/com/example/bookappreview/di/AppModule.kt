package com.example.bookappreview.di

import android.content.Context
import androidx.room.Room
import com.example.bookappreview.data.database.AppDatabase
import com.example.bookappreview.data.database.dao.LivroSalvoDao
import com.example.bookappreview.data.repository.LivroRepositoryImpl
import com.example.bookappreview.data.webclient.BookService
import com.example.bookappreview.data.webclient.aiservice.AiService
import com.example.bookappreview.domain.repository.LivroRepository
import com.example.bookappreview.domain.usecase.livro.BuscarLivrosUseCase
import com.example.bookappreview.domain.usecase.livro.SalvarLivrosUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLivroSalvoDao(database: AppDatabase): LivroSalvoDao {
        return database.livroSalvodao()
    }

    @Provides
    @Singleton
    fun provideBookService(): BookService {
        return BookService()
    }

    @Provides
    @Singleton
    fun provideAiService(): AiService {
        return AiService()
    }

    @Provides
    @Singleton
    fun provideLivroRepository(
        livroSalvoDao: LivroSalvoDao,
        bookService: BookService,
        aiService: AiService
    ): LivroRepository {
        return LivroRepositoryImpl(livroSalvoDao, bookService, aiService)
    }

    @Provides
    @Singleton
    fun provideSalvarLivrosUsecase(livroRepository: LivroRepository): SalvarLivrosUsecase {
        return SalvarLivrosUsecase(livroRepository)
    }

    @Provides
    @Singleton
    fun provideBuscarLivrosUseCase(livroRepository: LivroRepository): BuscarLivrosUseCase {
        return BuscarLivrosUseCase(livroRepository)
    }
}
