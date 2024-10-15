package com.example.bookappreview.presentation.states

import com.example.bookappreview.presentation.model.LivroParcelable

data class SearchScreenUiState(
    //Estado Inicial -> Uma string vazia
    val query: String = "", // O texto atual do campo de busca

    // O estado da pesquisa, que pode ser Idle, Loading, Success ou Error
    // Sendo SearchState.Idle o estado inicial
    val searchState: SearchState = SearchState.Idle // O estado da pesquisa
)

sealed class SearchState {
    data object Idle : SearchState() // Estado inicial, nada sendo pesquisado ainda
    data object Loading : SearchState() // Estado de carregamento
    data class Success(val livros: List<LivroParcelable>) : SearchState() // Estado de sucesso com a lista de livros
    data class Error(val message: String) : SearchState() // Estado de erro com mensagem
}