package com.example.bookappreview.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookappreview.R
import com.example.bookappreview.presentation.components.MyDivider
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel

@Composable
fun BookDetailsScreen(
    navController: NavHostController,
    viewModel: BookSharedViewModel
) {
    // Observa o livro selecionado
    val selectedBook by viewModel.selectedBook.collectAsState()
    Log.i("TAG", "BookDetailsScreen: Livro no sharedViewmodel = $selectedBook")
//    BookDetailsContent(book = selectedBook!!)
}

@Composable
fun BookDetailsContent(book: LivroParcelable) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181b20))
            .padding(16.dp)
    ) {
        // Capa + informações do livro
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1.5f)
            ) {
                // Infos - título, autor, ano
                Text(
                    text = "uiState.title",
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "• Written by ",
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
                Text(
                    text = "uiState.title",
                    style = MaterialTheme.typography.h6,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(100.dp))
            }

            // Capa do livro
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background), // Substitua com a imagem correta
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(160.dp)
                    .width(120.dp)
            )
        }

        // Descrição do livro
        Text(
            text = book.description!!,
            style = MaterialTheme.typography.body1,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(14.dp))

        MyDivider(modifier = Modifier.fillMaxWidth())
    }
}


//@Preview(showBackground = true)
//@Composable
//private fun BookDetailsScreenPreview() {
//    BookDetailsScreen(
//        navController = NavHostController(LocalContext.current),
//        detailsViewModel = BookDetalisScreensViewModel(),
//        sharedViewModel = BookSharedViewModel()
//    )
//}