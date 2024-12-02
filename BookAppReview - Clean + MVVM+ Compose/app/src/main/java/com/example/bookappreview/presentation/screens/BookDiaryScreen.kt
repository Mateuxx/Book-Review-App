package com.example.bookappreview.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.bookappreview.R
import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.presentation.themes.Orange700
import com.example.bookappreview.presentation.viewModel.BookDiaryViewModel


@Composable
fun BookDiaryScreen(
    navController: NavHostController,
    viewModel: BookDiaryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Passa os grupos diretamente para o conteúdo
    BookDiaryContent(groupedReviews = uiState.groupedReviews)
}

@Composable
fun BookDiaryContent(groupedReviews: Map<String, List<Livro>>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181b20))
    ) {
        // Ordena os grupos em ordem decrescente
        groupedReviews.toSortedMap().forEach { (monthYear, reviews) ->
            Log.i("TAG", "BookDiaryContent: $groupedReviews")
            // Cabeçalho do grupo
            item {
                GroupHeader(monthYear)
            }
            // Livros no grupo
            items(reviews) { book ->
                BookReviewItem(
                    book = book,
                    rating = book.rated,
                    liked = book.like,
                    onRatingChanged = { newRating ->
                        println("Novo rating: $newRating para o livro ${book.title}")
                    },
                    onLikeChanged = {
                        println("Like alterado para o livro ${book.title}")
                    }
                )
            }
        }
    }
}

@Composable
fun GroupHeader(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1E1E1E)) // Fundo cinza escuro
            .padding(8.dp),
        color = Color.Gray
    )
}

@Composable
fun BookReviewItem(
    book: Livro,
    rating: Int,
    liked: Boolean,
    onRatingChanged: (Int) -> Unit,
    onLikeChanged: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Imagem do Livro
        AsyncImage(
            model = book.imagem,
            contentDescription = null,
            modifier = Modifier
                .height(60.dp)
                .width(40.dp)
                .size(60.dp)
                .background(Color.Gray),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.imagem_padrao)
        )
        Spacer(modifier = Modifier.width(16.dp))

        // Detalhes do Livro
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = book.title ?: "Título Desconhecido",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = book.year ?: "Ano Desconhecido",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Ações (estrelas e like)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row {
                (1..5).forEach { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = if (index <= rating) Color.Green else Color.DarkGray,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onRatingChanged(index) }
                    )
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Like",
                tint = if (liked) Orange700 else Color.Gray,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onLikeChanged() }
            )
        }
    }
    // Adiciona um divisor abaixo de cada item
    Divider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun BookReviewItemPreview() {
    val livro = Livro(
        title = "The Hitchhiker's Guide to the Galaxy",
        subtitle = "A Trilogy in Five Parts",
        publisher = "Pan Books",
        imagem = "https://images.gr-assets.com/books/1349686523m/38670.jpg",
        description = "The Hitchhiker's Guide to the Galaxy is a comedy science fiction series created by Douglas Adams.",
        pageCount = 224,
        year = "1979",
        autor = "Douglas Adams",
        genero = "Science Fiction",
        rated = 4,
        like = true,
        review = "A funny and thought-provoking book.",
        dateReview = "2023-10-27"
    )

    BookReviewItem(
        book = livro,
        rating = 4,
        liked = true,
        onRatingChanged = { /*TODO*/ },
        onLikeChanged = { /*TODO*/ }
    )
}

@Preview(showBackground = true)
@Composable
fun GroupHeaderPreview() {
    GroupHeader(title = "Group Title")
}
