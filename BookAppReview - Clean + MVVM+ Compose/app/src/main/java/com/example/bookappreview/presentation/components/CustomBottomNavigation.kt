package com.example.bookappreview.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomNavigation(
    modifier: Modifier = Modifier,
//    selectedTab: Int,
//    onTabSelected: (Int) -> Unit, // Callback to handle tab selection
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFF445565)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        //Home Button
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                Icons.Default.Home,
                contentDescription = "Home",
                tint = Color.White
            )
        }
        //Search Button
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White
            )
        }

        //Profile Button
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = "Search",
                tint = Color.White
            )
        }


    }

}

@Preview(showBackground = true)
@Composable
private fun CustomBottomNavigationPreview() {
    CustomBottomNavigation()
}