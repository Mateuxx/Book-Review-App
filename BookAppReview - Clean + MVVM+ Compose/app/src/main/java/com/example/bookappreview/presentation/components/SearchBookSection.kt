package com.example.bookappreview.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookappreview.presentation.model.LivroParcelable

@Composable
fun SearchBookSection(book: LivroParcelable, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(6.dp)
    ) {
        Row {
            BookPosterItem(
                book = book, modifier = Modifier
                    .width(90.dp)
                    .height(120.dp)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                //titulo
                Text(
                    text = book.title!!, style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
                //Autor
                Text(
                    text = book.autor!!, style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp
                    )
                )

                // Ano
                Text(
                    text = book.year!!, style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp
                    )
                )
            }
        }
    }
}


//@Preview(showBackground = true, backgroundColor = 0xFF181b20)
//@Composable
//private fun SearchBookSectionPreview() {
//    SearchBookSection(
//        book = LivroParcelable(
//
//        )
//        onClick = {}
//    )
//}