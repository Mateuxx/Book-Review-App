package com.example.bookappreview.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyDivider() {
    Divider(
        color = Color.Gray, // Adjust the color as per your preference
        thickness = 1.dp,   // Adjust the thickness as needed
        modifier = Modifier
            .fillMaxWidth() // Makes the divider span the full width
            .height(1.dp)   // Set the height for the divider line
            .padding(horizontal = 5.dp)
    )
}