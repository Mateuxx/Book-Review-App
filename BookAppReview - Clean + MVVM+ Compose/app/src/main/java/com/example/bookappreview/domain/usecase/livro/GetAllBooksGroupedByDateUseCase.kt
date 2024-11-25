package com.example.bookappreview.domain.usecase.livro

import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.domain.repository.LivroRepository
import kotlinx.coroutines.flow.Flow


class GetAllBooksGroupedByDateUseCase(private val repository: LivroRepository) {

    operator fun invoke(): Flow<Map<String, List<Livro>>> {
        return repository.fetchAllBooksGroupedByDate()
    }
}