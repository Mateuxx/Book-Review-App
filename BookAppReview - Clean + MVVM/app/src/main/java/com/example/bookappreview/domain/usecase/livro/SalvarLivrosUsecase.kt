package com.example.bookappreview.domain.usecase.livro

import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.domain.repository.LivroRepository

class SalvarLivrosUsecase(
    private val livroRepo: LivroRepository
) {
    /**
     * Invoke eh uma convensão dentro de usecase para chamarmos de forma mais limpa a classe
     * diretamente pelo metodo sem a necessidade de fazer o instanciamento do objeto
     */
    suspend operator fun invoke(livro: Livro) {
        livroRepo.saveBooks(livro)
    }
}