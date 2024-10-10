package com.example.bookappreview.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomTabRow(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .height(30.dp)
    ) {
        tabs.forEachIndexed { index, title ->
            Box(
                modifier = Modifier
                    .weight(10f)
                    .width(45.dp)
                    .fillMaxHeight()
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(3.dp))
                    .background(
                        if (selectedTabIndex == index) Color(0xFF445565) else Color.Black, // Cor de fundo condicional
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable {
                        onTabSelected(index)
                    },
                contentAlignment = Alignment.Center // Alinha o conteúdo ao centro
            ) {
                Text(
                    text = title,
                    color = if (selectedTabIndex == index) Color.White else Color.Gray // Cor do texto condicional
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomTabRowPreview() {
     CustomTabRow(tabs = listOf("Livros", "Reviews", "Lists"), selectedTabIndex = 0) {
         
     }
}
