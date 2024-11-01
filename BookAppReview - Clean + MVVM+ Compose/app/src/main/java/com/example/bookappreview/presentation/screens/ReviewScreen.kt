package com.example.bookappreview.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.bookappreview.presentation.components.ReviewContent
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel
import com.example.bookappreview.presentation.viewModel.ReviewScreenViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReviewsScreen(
    navController: NavHostController,
    sharedViewModel: BookSharedViewModel,
    reviewViewModel: ReviewScreenViewModel
) {

    val selectedBook by sharedViewModel.selectedBook.collectAsState()

    Log.i("TAG", "ReviewsScreen: Livro em ReviewScreen = $selectedBook")

    LaunchedEffect(selectedBook) {
        selectedBook?.let { book ->
            reviewViewModel.initializeBook(book) //pega o valor do livro do sharedViewModel
        }
    }

    val uiState by reviewViewModel.uiState.collectAsState()


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()



    // Verifica o estado de `isSaving` e exibe o Snackbar
    LaunchedEffect(uiState.isSaving) {
        if (uiState.isSaving) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Salvando sua Review...")
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        uiState.book?.let { book ->
            ReviewContent(
                book = book,
                rating = uiState.rating,
                liked = uiState.liked,
                reviewText = uiState.reviewString,
                date = uiState.date,
                onRatingChanged = { reviewViewModel.updateRating(it) },
                onLikeChanged = { reviewViewModel.toggleLiked() },
                onReviewTextChange = { reviewViewModel.updateReviewText(it) },
                onSaveClick = {
                    reviewViewModel.saveBook()
                    //save and go to home screen(which is bookscreen) and
                    // send the flag to upadte the bookscreen with the lastest books row
                }
            )
        } ?: run {
            Text(text = "Nenhum Livro Selecionado", color = Color.Red)
        }
    }
}
