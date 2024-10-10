package com.example.bookappreview.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookappreview.presentation.model.LivroParcelable

@Composable
fun BookSection(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top, // Centraliza verticalmente
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Salve",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 5.dp)

        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(10) {
                BookPosterItem(
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
                    )
                )
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        MyDivider()


    }
}

@Preview(showSystemUi = true)
@Composable
private fun BookSectionPreview() {
    BookSection()
}