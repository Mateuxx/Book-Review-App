package com.example.bookappreview.presentation.screens

import android.util.Log
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
import androidx.navigation.NavHostController
import com.example.bookappreview.presentation.components.BookSection
import com.example.bookappreview.presentation.states.BooksScreenUiState
import com.example.bookappreview.presentation.viewModel.BooksScreenViewModel
import com.example.bookappreview.presentation.viewModel.ReviewScreenViewModel

@Composable
fun BooksScreen(
    navController: NavHostController,
    bookScreenViewModel: BooksScreenViewModel,
    reviewScreenViewModel: ReviewScreenViewModel,
    isLoading: Boolean
) {
    val context = LocalContext.current
    val uiState by bookScreenViewModel.uiState.collectAsState()
    val saveComplete by reviewScreenViewModel.saveCompleteEvent.collectAsState()

    // Atualiza a lista de livros quando um novo livro é salvo
    LaunchedEffect(saveComplete) {
        if (saveComplete) {
            Log.d("BooksScreen", "saveComplete detectado, chamando refreshBooks")
            bookScreenViewModel.refreshBooks("Harry Potter", context)
            reviewScreenViewModel.resetSaveComplete()
        }
    }

    // Carrega livros e recomendações apenas uma vez ao entrar na tela
    LaunchedEffect(Unit) {
        if (uiState !is BooksScreenUiState.Success) {
            Log.d("BooksScreen", "Primeiro carregamento de livros e recomendações")
            bookScreenViewModel.refreshBooks("Harry Potter", context)
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
                BookSection("Latest", emptyList(), isLoading)
                BookSection("Want To Read", emptyList(), isLoading)
                BookSection("Recomendados", emptyList(), isLoading)
            }

            is BooksScreenUiState.Success -> {
                val successState = uiState as BooksScreenUiState.Success
                BookSection("Ultimos", successState.livros, isLoading = false)
                BookSection("Want To Read", successState.livros, isLoading = false)
                BookSection("Recomendados", successState.livrosRecomendados, isLoading = false)
            }

            is BooksScreenUiState.Error -> {
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