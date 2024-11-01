package com.example.bookappreview.domain.usecase.livro

import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.domain.repository.LivroRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookFromDatabaseUseCase @Inject constructor(
    private val livroRepository: LivroRepository
) {
    operator fun invoke(): Flow<List<Livro>> {
        return livroRepository.fecthLastSavedBooks()

    }
}