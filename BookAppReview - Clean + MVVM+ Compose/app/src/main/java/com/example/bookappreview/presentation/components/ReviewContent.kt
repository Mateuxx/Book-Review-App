package com.example.bookappreview.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
    var rating by remember {
        mutableStateOf(4)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181b20))
            .padding(16.dp)
    ) {
        //cancelar save (Header
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = SpaceBetween
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

        Spacer(modifier = Modifier.height(20.dp))

        //Moview info - (cover + title + year)
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
            Text(
                text = "The Fall Guy",
                modifier = Modifier.padding(start = 6.dp),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "2024",
                modifier = Modifier.padding(start = 3.dp),
                color = Color.Gray,
                fontSize = 14.sp
            )

        }

        //Date Section
        Row {

        }

        //Rating Section + liked
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
        ) {

        }

        //Review Section

    }

}

@Preview(showBackground = true)
@Composable
private fun ReviewContentPreview() {
    ReviewContent()
}