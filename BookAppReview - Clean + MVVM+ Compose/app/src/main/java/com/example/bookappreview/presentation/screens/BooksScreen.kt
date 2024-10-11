package com.example.bookappreview.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookappreview.di.Injection
import com.example.bookappreview.presentation.components.BookSection
import com.example.bookappreview.presentation.viewModel.BooksScreenViewModel
import com.example.bookappreview.presentation.viewModel.factory.BooksScreenViewModelFactory

@Composable
fun BooksScreen(
    bookScreenViewModel: BooksScreenViewModel = viewModel(
        factory = BooksScreenViewModelFactory(
            buscaLivrosUseCase = Injection.provideBuscaLivrosUsecase(LocalContext.current)
        )
    )
) {


    val books by bookScreenViewModel.livros.collectAsState() //obtem a lista de livros

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        bookScreenViewModel.fetchBooks("Harry Potter", context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181b20))
    ) {

        LazyRow {

        }

        BookSection(
            sectionTitle = "Want To Read",
            books = books
        )
//        BookSection()
//        BookSection()

    }
}

@Preview
@Composable
private fun BooksScreenPreview() {
    val fakeViewModel: BooksScreenViewModel = viewModel(
    factory = BooksScreenViewModelFactory(
        buscaLivrosUseCase = Injection.provideBuscaLivrosUsecase(LocalContext.current)
    ) )
    BooksScreen(fakeViewModel)
}