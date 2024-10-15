package com.example.bookappreview.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = { newQuery -> onQueryChange(newQuery) },
        label = { Text("Pesquisar Livro") },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .width(80.dp)
            .padding(8.dp),

        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search Icon"
            )
        },
        // Para a cor se manter em branca ao clicar no icone
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedLabelColor = MaterialTheme.colors.primary,
            unfocusedLabelColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
            textColor = MaterialTheme.colors.onSurface
        )
    )
}

//@Preview
//@Composable
//private fun SearchBarPreview() {
//    SearchBar(query = , onQueryChange = )
//}