package com.example.bookappreview.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookappreview.presentation.components.BookSection
import com.example.bookappreview.presentation.components.SearchBar
import com.example.bookappreview.presentation.components.SearchBookSection
import com.example.bookappreview.presentation.model.LivroParcelable

@Composable
fun SearchBookScreen(navController: NavHostController) {

    // Estados locais da UI - ViewModel Later
    var query by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var books by remember { mutableStateOf(listOf<LivroParcelable>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF181b20))
    ) {
        //Search Book TextField
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            modifier = Modifier.height(20.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(10) { book ->
                SearchBookSection(onClick = {})

            }

        }
    }
}

    @Preview(showBackground = true)
    @Composable
    private fun SearchBookScreenPreview() {
        SearchBookScreen(navController = NavHostController(LocalContext.current))
    }