package com.example.bookappreview.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookappreview.presentation.components.CustomBottomNavigation
import com.example.bookappreview.presentation.components.CustomTabRow
import com.example.bookappreview.presentation.states.Screen
import com.example.bookappreview.presentation.viewModel.MainViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainViewModel = viewModel() // Injeta o ViewModel
) {
    // Observa o estado da UI
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Renderiza o Título
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Book Diary",
                color = Color.White,
                fontSize = 24.sp
            )
        }

        // Renderiza o CustomTabRow com as abas superiores apenas na tela Home (Books, Reviews, Lists)
        if (uiState.selectedBottomNavIndex == 0) { // Exibe o TabRow apenas na aba Home
            Spacer(modifier = Modifier.height(8.dp))

            CustomTabRow(
                tabs = viewModel.tabs,
                selectedTabIndex = uiState.selectedTabIndex,
                onTabSelected = { index ->
                    viewModel.onTabSelected(index) // Atualiza o estado das abas no ViewModel
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // O conteúdo principal deve ocupar o espaço restante da tela
        Box(
            modifier = Modifier
                .weight(1f) // O conteúdo principal ocupa o espaço restante
                .fillMaxWidth()
        ) {
            when (uiState.currentScreen) {
                Screen.Books -> BooksScreen(navController = navController)
                Screen.Reviews -> ReviewsScreen(navController = navController)
                Screen.Lists -> ListScreen(navController = navController)
                Screen.Search -> SearchBookScreen(navController = navController)
                Screen.Profile -> ProfileScreen(navController = navController)
            }
        }

        // CustomBottomNavigation para alternar entre Home, Search e Profile
        CustomBottomNavigation(
            selectedTab = uiState.selectedBottomNavIndex,
            onTabSelected = { index ->
                viewModel.onBottomNavItemSelected(index) // Atualiza o estado da navegação no ViewModel
            }
        )
    }
}






@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
//    // Criamos uma instância "fake" do ViewModel dentro do Preview, para simular o comportamento.
//    val fakeViewModel = MainViewModel()
//
//    // Chamamos diretamente a HomeScreen passando o ViewModel
//    MainScreen(
//        homeViewModel = fakeViewModel,
//        navController = NavHostController(LocalContext.current)
//    )
}
