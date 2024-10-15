package com.example.bookappreview.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookappreview.presentation.model.LivroParcelable

@Composable
fun SearchBookScreen(navController: NavHostController) {
    // Estados locais da UI - ViewModel Later
    var query by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var books by remember { mutableStateOf(listOf<LivroParcelable>()) }

    Column {
        //Search Book TextField
        OutlinedTextField(
            value = query,
            onValueChange = { newQuery -> query = newQuery },
            label = { Text("Pesquisar Livro") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search Icon"
                )
            }
        )
    }
}