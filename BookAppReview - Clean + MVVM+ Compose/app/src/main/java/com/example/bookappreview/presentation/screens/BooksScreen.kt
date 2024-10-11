package com.example.bookappreview.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.bookappreview.presentation.viewModel.BooksScreenViewModel
import com.example.bookappreview.presentation.viewModel.factory.BooksScreenViewModelFactory

@Composable
fun BooksScreen(
    navController: NavHostController, // O NavBackStackEntry é passado aqui
    bookScreenViewModel: BooksScreenViewModel = viewModel(
        factory = BooksScreenViewModelFactory(
            buscaLivrosUseCase = Injection.provideBuscaLivrosUsecase(LocalContext.current)
        )
    )
) {

    val books by bookScreenViewModel.livros.collectAsState() //obtem a lista de livros

    val recommendBooks by bookScreenViewModel.livrosRecomendados.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {

        if (books.isEmpty()) {
            bookScreenViewModel.fetchBooks("Harry Potter", context)
        }

        if (recommendBooks.isEmpty()) {
            val recommendations = bookScreenViewModel.aiRecommendation("Harry Potter")
            bookScreenViewModel.fetchBooksRecomendados(recommendations, context)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181b20))
    ) {

        //Latest Books
        Column(
            Modifier.padding(
                horizontal = 10.dp,
                vertical = 8.dp
            )
        ) {
            LazyRow(
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(books) { book ->
                    BookPosterItem(book = book)
                }

            }
            MyDivider()
        }

        BookSection(
            sectionTitle = "Want To Read",
            books = books
        )
        BookSection(
            sectionTitle = "teste",
            books = recommendBooks
        )
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