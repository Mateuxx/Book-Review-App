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
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    // Lógica da navegação dentro da CustomBottomNavigation
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFF445565)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onTabSelected(0) },
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = if (selectedTab == 0) Color.Cyan else Color.Gray
            )
        }
        IconButton(
            onClick = { onTabSelected(1) },
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = if (selectedTab == 1) Color.Cyan else Color.Gray
            )
        }
        IconButton(
            onClick = { onTabSelected(2) },
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = if (selectedTab == 2) Color.Cyan else Color.Gray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CustomBottomNavigationPreview() {
    CustomBottomNavigation(
        selectedTab = 0,
        onTabSelected = {}
    )
}