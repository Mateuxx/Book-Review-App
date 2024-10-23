package com.example.bookappreview.presentation.screens

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bookappreview.presentation.components.ReviewContent
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel
import com.example.bookappreview.presentation.viewModel.ReviewScreenViewModel

@Composable
fun ReviewsScreen(
    navController: NavHostController,
    sharedViewModel: BookSharedViewModel,
    reviewViewModel: ReviewScreenViewModel = viewModel(),
) {

    val selectedBook by sharedViewModel.selectedBook.collectAsState()
    Log.i("TAG", "ReviewsScreen: Livro em ReviewScreen = $selectedBook")

    LaunchedEffect(selectedBook) {
        selectedBook?.let { book ->
            reviewViewModel.initializeBook(book) //pega o valor do livro do sharedViewModel
        }
    }

    val uiState by reviewViewModel.uiState.collectAsState()
    uiState.book?.let { book ->
        ReviewContent(
            book = book,
            rating = uiState.rating,
            liked = uiState.liked,
            reviewText = uiState.reviewString,
            onRatingChanged = { reviewViewModel.updateRating(it) },
            onLikeChanged = { reviewViewModel.toggleLiked() },
            onReviewTextChange = { reviewViewModel.updateReviewText(it) }
        )
    } ?: run {
        Text(text = "Nenhum Livro Selecionado", color = Color.Red)
    }

}