package com.example.bookappreview.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.bookappreview.R
import com.example.bookappreview.presentation.components.MyDivider
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.presentation.navigation.Screen
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel
import com.example.bookappreview.presentation.viewModel.DetailsScreenViewModel

@Composable
fun BookDetailsScreen(
    navController: NavHostController,
    sharedViewModel: BookSharedViewModel,
    detailsViewModel: DetailsScreenViewModel = viewModel()
) {
    // Observa o livro selecionado
    val selectedBook by sharedViewModel.selectedBook.collectAsState()
    Log.i("TAG", "BookDetailsScreen: Livro no sharedViewmodel = $selectedBook")
    BookDetailsContent(book = selectedBook!!, navController)
}

@Composable
fun BookDetailsContent(book: LivroParcelable, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181b20))
    ) {
        // Arrow Back no topo da tela
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), // Padding para não colar no canto da tela
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Voltar",
                tint = Color.White,
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        navController.popBackStack() // Volta para a tela anterior
                    }
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Capa + informações do livro
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Adicione um pouco de padding lateral
        ) {
            Column(
                modifier = Modifier
                    .weight(1.5f)
            ) {
                // Infos - título, autor, ano
                Text(
                    text = book.title ?: "Titulo não encontrado",
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "${book.year ?: "Ano não encontrado"} • Written by ",
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )

                Text(
                    text = book.autor ?: "Autor não encontrado",
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Capa do livro
            AsyncImage(
                model = book.imagem,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(160.dp)
                    .width(120.dp),
                placeholder = painterResource(id = R.drawable.imagem_padrao)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Descrição do livro
        Text(
            text = book.description!!,
            style = MaterialTheme.typography.body1,
            color = Color.Gray,
            maxLines = 14,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        MyDivider(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))

        Spacer(modifier = Modifier.height(14.dp))

        // Ícones no final da tela
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { }
                )

                Text(
                    text = "Read",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            navController.navigate(Screen.Reviews.route)
                        }
                )

                Text(
                    text = "Review",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.WatchLater,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { }
                )

                Text(
                    text = "Read List",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                )
            }
        }
    }
}


@Preview
@Composable
private fun BookDetailsContentPreview() {
    BookDetailsContent(
        book = LivroParcelable(
            title = "The Adventures of Kotlin",
            subtitle = "A Comprehensive Guide to Jetpack Compose",
            publisher = "Compose Publishers",
            imagem = "https://example.com/image-of-book.jpg",
            description = "This book provides an in-depth guide to Jetpack Compose and Kotlin with practical examples and best practices.",
            pageCount = 320,
            year = "2024",
            autor = "John Doe",
            genero = "Technology"
        ),
        navController = NavHostController(LocalContext.current)
    )
}

