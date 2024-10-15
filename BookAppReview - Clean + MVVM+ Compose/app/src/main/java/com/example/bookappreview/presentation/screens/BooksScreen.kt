package com.example.bookappreview.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.bookappreview.presentation.components.BookSection
import com.example.bookappreview.presentation.states.BooksScreenUiState
import com.example.bookappreview.presentation.viewModel.BooksScreenViewModel
import com.example.bookappreview.presentation.viewModel.factory.BooksScreenViewModelFactory

@Composable
fun BooksScreen(
    navController: NavHostController,
    bookScreenViewModel: BooksScreenViewModel = viewModel(
        factory = BooksScreenViewModelFactory(
            buscaLivrosUseCase = Injection.provideBuscaLivrosUsecase(LocalContext.current)
        )
    )
) {
    // Observa o estado de UI
    val uiState by bookScreenViewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        // Evita fazer novas requisições se os dados já foram carregados
        if (uiState !is BooksScreenUiState.Success) {
            bookScreenViewModel.fetchBooks("Harry Potter", context)

            val recommendations = bookScreenViewModel.aiRecommendation("Harry Potter")
            bookScreenViewModel.fetchBooksRecomendados(recommendations, context)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181b20))
    ) {
        when (uiState) {
            is BooksScreenUiState.Loading -> {
                // Exibe shimmer enquanto carrega
                BookSection(
                    sectionTitle = "Latest",
                    books = emptyList(),
                    isLoading = true
                )
                BookSection(
                    sectionTitle = "Want To Read",
                    books = emptyList(),
                    isLoading = true
                )
                BookSection(
                    sectionTitle = "Recomendados",
                    books = emptyList(),
                    isLoading = true
                )
            }
            is BooksScreenUiState.Success -> {
                val successState = uiState as BooksScreenUiState.Success

                // Exibe os livros principais e recomendados
                BookSection(
                    sectionTitle = "Ultimos",
                    books = successState.livros,
                    isLoading = false // Carregamento completo, exibe os livros
                )
                BookSection(
                    sectionTitle = "Want To Read",
                    books = successState.livros,
                    isLoading = false // Carregamento completo, exibe os livros
                )

                BookSection(
                    sectionTitle = "Recomendados",
                    books = successState.livrosRecomendados,
                    isLoading = false // Carregamento completo, exibe os recomendados
                )
            }
            is BooksScreenUiState.Error -> {
                // Exibe uma mensagem de erro
                Text(
                    text = (uiState as BooksScreenUiState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


//@Preview
//@Composable
//private fun BooksScreenPreview() {
//    val fakeViewModel: BooksScreenViewModel = viewModel(
//        factory = BooksScreenViewModelFactory(
//            buscaLivrosUseCase = Injection.provideBuscaLivrosUsecase(LocalContext.current)
//        )
//    )
//    BooksScreen(fakeViewModel)
//}