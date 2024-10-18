package com.example.bookappreview.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bookappreview.di.Injection
import com.example.bookappreview.presentation.components.SearchBar
import com.example.bookappreview.presentation.components.SearchBookSection
import com.example.bookappreview.presentation.navigation.Screen
import com.example.bookappreview.presentation.states.SearchState
import com.example.bookappreview.presentation.viewModel.BookSearchViewModel
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel
import com.example.bookappreview.presentation.viewModel.factory.BookSearchViewModelFactory

@Composable
fun SearchBookScreen(
    navController: NavHostController,
    sharedViewModel: BookSharedViewModel,
    viewModel: BookSearchViewModel = viewModel(
        factory = BookSearchViewModelFactory(
            buscarLivrosUseCase = Injection.provideBuscaLivrosUsecase(LocalContext.current)
        )
    )
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current // Contexto local

    // Utilizando LaunchedEffect para resetar o estado ao entrar na tela
    LaunchedEffect(Unit) {
        viewModel.resetState() // Resetar o estado da pesquisa
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181b20))
    ) {
        //Search Book TextField
        SearchBar(
            query = uiState.query,
            onQueryChange = { newQuery -> viewModel.onQueryChanged(newQuery, context) },
            modifier = Modifier.height(20.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Lista de SearchBookSections
        // Observa o estado da pesquisa no UI State
        when (uiState.searchState) {
            is SearchState.Idle -> {
                Text("Digite algo para buscar livros.", color = Color.Gray)
            }

            is SearchState.Loading -> {
                CircularProgressIndicator() // Exibe um indicador de carregamento
            }

            is SearchState.Success -> {
                val livros = (uiState.searchState as SearchState.Success).livros
                LazyColumn {
                    items(livros) { livro ->
                        SearchBookSection(book = livro, onClick = {
                            Log.i("TAG", "SearchBookScreen: Livro Selecionado: $livro")
                            sharedViewModel.selectBook(livro) //coloca esse livro especifico na sharedViewModel
                            navController.navigate(Screen.Details.route)
                        }
                        )
                    }
                }
            }

            is SearchState.Error -> {
                Text((uiState.searchState as SearchState.Error).message, color = Color.Red)
            }
        }
    }
}

//
//@Preview(showBackground = true)
//@Composable
//private fun SearchBookScreenPreview() {
//    SearchBookScreen(
//        navController = NavHostController(
//            LocalContext.current,
//        )
////        sharedViewModel = BookSharedViewModel()
//    )
//}