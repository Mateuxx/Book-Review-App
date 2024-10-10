package com.example.bookappreview.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.bookappreview.R
import com.example.bookappreview.presentation.model.LivroParcelable

@Composable
fun BookPosterItem(
    modifier: Modifier = Modifier,
    book: LivroParcelable
) {
    Column{
        AsyncImage(
            model = book.imagem,
            contentDescription = null,
            modifier = modifier
                .height(150.dp)
                .width(100.dp)
                .padding(2.dp),
            placeholder = painterResource(
                id = R.drawable.ic_launcher_background
            ),
            contentScale = ContentScale.Crop
        )
    }


}

@Preview(showBackground = true)
@Composable
private fun BookPosterItemPreview() {
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