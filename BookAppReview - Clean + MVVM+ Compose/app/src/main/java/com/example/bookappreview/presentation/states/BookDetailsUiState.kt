package com.example.bookappreview.presentation.states

data class BookDetailsUiState(
    val title: String = "Título desconhecido",
    val author: String = "Autor desconhecido",
    val year: String = "Ano desconhecido",
    val description: String = "Descrição indisponível"
)