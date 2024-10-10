package com.example.bookappreview.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookappreview.presentation.components.CustomTabRow
import com.example.bookappreview.presentation.viewModel.MainViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    homeViewModel: MainViewModel = viewModel() // ViewModel gerencia o estado
) {
    // Observa o índice da aba selecionada
    val selectedTabIndex by homeViewModel.selectedTabIndex.collectAsState()

    // Observa o conteúdo da aba selecionada
    val tabContent by homeViewModel.tabContent.collectAsState()

    // Obtém a lista de tabs do ViewModel
    val tabs = homeViewModel.tabs

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(8.dp)
    ) {

        // Renderiza o CustomTabRow com os tabs e o índice selecionado
        CustomTabRow(
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { homeViewModel.onTabSelected(it) } // Atualiza a aba no ViewModel
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Renderiza o conteúdo da aba com base no estado gerenciado pelo ViewModel
        Text(tabContent, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // Criamos uma instância "fake" do ViewModel dentro do Preview, para simular o comportamento.
    val fakeViewModel = MainViewModel()

    // Chamamos diretamente a HomeScreen passando o ViewModel
    MainScreen(homeViewModel = fakeViewModel)
}
