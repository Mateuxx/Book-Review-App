package com.example.bookappreview.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookappreview.presentation.components.CustomBottomNavigation
import com.example.bookappreview.presentation.components.CustomTabRow
import com.example.bookappreview.presentation.viewModel.MainViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: MainViewModel = viewModel()
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
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Book Diary",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Renderiza o CustomTabRow com os tabs e o índice selecionado
        CustomTabRow(
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { index ->
                homeViewModel.onTabSelected(index)
                // Navegação entre as telas com base na aba selecionada
                when (index) {
                    0 -> navController.navigate("books") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                    1 -> navController.navigate("reviews") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                    2 -> navController.navigate("lists") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // O conteúdo da tela (NavHost) deve ocupar o espaço restante
        NavHost(
            modifier = Modifier.weight(1f), // Faz o conteúdo ocupar o espaço restante da tela
            navController = navController,
            startDestination = "books"
        ) {
            composable("books") {
                BooksScreen(navController = navController)
            }
            composable("reviews") {
                ReviewsScreen(navController = navController)
            }
            composable("lists") {
                ListScreen(navController = navController)
            }
        }

        // Colocado no final e fixo na parte inferior da tela
        CustomBottomNavigation()
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // Criamos uma instância "fake" do ViewModel dentro do Preview, para simular o comportamento.
    val fakeViewModel = MainViewModel()

    // Chamamos diretamente a HomeScreen passando o ViewModel
    MainScreen(
        homeViewModel = fakeViewModel,
        navController = NavHostController(LocalContext.current)
    )
}
