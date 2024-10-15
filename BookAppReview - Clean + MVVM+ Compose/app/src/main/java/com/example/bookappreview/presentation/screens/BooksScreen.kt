package com.example.bookappreview.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.bookappreview.di.Injection
import com.example.bookappreview.presentation.components.BookPosterItem
import com.example.bookappreview.presentation.components.BookSection
import com.example.bookappreview.presentation.components.MyDivider
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
    val uiState by bookScreenViewModel.uiState.collectAsState() // Observa o UI State
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        // Evita buscar os livros novamente se eles já foram carregados
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
                // Exibe uma interface de carregamento, por exemplo, um CircularProgressIndicator
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            is BooksScreenUiState.Success -> {
                // Estado de sucesso: exibe os livros e livros recomendados
                val successState = uiState as BooksScreenUiState.Success

                // Exibe os livros populares
                Column(
                    Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
                ) {
                    LazyRow(
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(successState.livros) { book ->
                            BookPosterItem(book = book)
                        }
                    }
                    MyDivider()
                }

                // Exibe as seções de livros
                BookSection(
                    sectionTitle = "Want To Read",
                    books = successState.livros
                )

                BookSection(
                    sectionTitle = "Recomendados",
                    books = successState.livrosRecomendados
                )
            }
            is BooksScreenUiState.Error -> {
                // Estado de erro: exibe uma mensagem de erro
                val errorState = uiState as BooksScreenUiState.Error
                // Exibe a mensagem de erro
                Text(
                    text = errorState.message,
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