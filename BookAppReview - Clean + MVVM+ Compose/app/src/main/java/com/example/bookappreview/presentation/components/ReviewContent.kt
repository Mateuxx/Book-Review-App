package com.example.bookappreview.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookappreview.R

@Composable
fun ReviewContent(modifier: Modifier = Modifier) {

    var liked by remember { mutableStateOf(false) }
    var rating by remember { mutableStateOf(4) }

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
            Image(
                painter = painterResource(id = R.drawable.imagem_padrao),
                contentDescription = "Book Cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(70.dp)
                    .width(50.dp),
            )
            Column(
                modifier = Modifier.padding(start = 6.dp)
            ) {
                Text(
                    text = "The Fall Guy",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "2024",
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
                text = "Monday, 21 October 2024",
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
                                .clickable { rating = index }
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
                    tint = if (liked) Color.Green else Color.DarkGray,
                    modifier = Modifier
                        .size(45.dp)
                        .clickable { liked = !liked }
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

        // Add Review Section
        Text(
            text = "Add review...",
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )


    }
}


@Preview(showBackground = true)
@Composable
private fun ReviewContentPreview() {
    ReviewContent(

    )
}