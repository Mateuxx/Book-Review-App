package com.example.bookappreview.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookappreview.R
import com.example.bookappreview.presentation.model.LivroParcelable

@Composable
fun ReviewContent(
    book: LivroParcelable,
    rating: Int,
    liked: Boolean,
    reviewText: String,
    onRatingChanged: (Int) -> Unit,
    onLikeChanged: () -> Unit,
    onReviewTextChange: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181b20))
            .padding(16.dp)
    ) {
        // Cancelar save (Header)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cancel",
                color = Color.Gray,
                fontSize = 18.sp,
                modifier = Modifier.clickable { }
            )

            Text(
                text = "I Read...",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { }
            )

            Text(
                text = "Save",
                color = Color.Green,
                fontSize = 18.sp,
                modifier = Modifier.clickable { }
            )
        }

        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            color = Color.Gray
        )

        // Movie info - (cover + title + year)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = book.imagem,
                modifier = Modifier
                    .height(80.dp)
                    .width(60.dp),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.imagem_padrao),

                )


            Column(
                modifier = Modifier.padding(start = 6.dp)
            ) {
                Text(
                    text = book.title ?: "Book Title",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = book.year ?: "Year",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            color = Color.Gray
        )

        // Date Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Date",
                color = Color.Gray,
                fontSize = 18.sp,
            )
            Text(
                text = "Monday, 21 October 2024", // TODO: Get the respect date
                color = Color.Gray,
                fontSize = 12.sp,
            )
        }

        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            color = Color.Gray
        )

        // Rating Section (Stars + Liked)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Rate", color = Color.Gray, fontSize = 18.sp)
                // Rating Stars
                Row(
                ) {
                    (1..5).forEach { index ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (index <= rating) Color.Green else Color.DarkGray,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable { onRatingChanged(index) }
                        )
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Like", color = Color.Gray, fontSize = 18.sp)
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = if (liked) Color.Green else Color.Yellow,
                    modifier = Modifier
                        .size(45.dp)
                        .clickable { onLikeChanged() }
                )
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            color = Color.Gray
        )

        OutlinedTextField(
            value = reviewText,
            onValueChange = { onReviewTextChange(it) },
            label = { Text(text = "Add review...", color = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .size(400.dp)
                .padding(6.dp),
            textStyle = TextStyle(color = Color.White)
        )

    }

}


@Preview(showBackground = true)
@Composable
private fun ReviewContentPreview() {
    ReviewContent(
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
        rating = 4,
        liked = true,
        reviewText = "This book is a must-read for Jetpack Compose developers.",
        onRatingChanged = {},
        onLikeChanged = {},
        onReviewTextChange = {}
    )
}