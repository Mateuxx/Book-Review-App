package com.example.bookappreview.presentation.screens


import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.navigation.compose.rememberNavController
import com.example.bookappreview.presentation.components.CustomBottomNavigation
import com.example.bookappreview.presentation.components.CustomTabRow
import com.example.bookappreview.presentation.states.Screen
import com.example.bookappreview.presentation.viewModel.MainViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel() // Injeta o ViewModel
) {
    // Criando o NavController dentro do Composable
    val navController = rememberNavController()

    // Observando o estado da UI
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

        Spacer(modifier = Modifier.height(8.dp))

        // Renderiza o CustomTabRow se a aba inferior for "Home"
        if (uiState.selectedBottomNavIndex == 0) {
            CustomTabRow(
                tabs = viewModel.tabs,
                selectedTabIndex = uiState.selectedTabIndex,
                onTabSelected = { index ->
                    viewModel.onTabSelected(index)
                    navController.navigate(
                        when (index) {
                            0 -> Screen.Books.route
                            1 -> Screen.Reviews.route
                            2 -> Screen.Lists.route
                            else -> Screen.Books.route
                        }
                    ) {
                        // Aqui utilizamos `restoreState` e `saveState` para restaurar o estado das telas anteriores
                        restoreState = true
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Define o NavHost, que gerencia as telas (destinos)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Books.route
            ) {
                composable(
                    route = Screen.Books.route,
                    enterTransition = { fadeIn() }, // Exemplo de animação ao entrar na tela
                    exitTransition = { fadeOut() }, // Exemplo de animação ao sair da tela
                    popEnterTransition = { fadeIn() }, // Exemplo de animação ao voltar para a tela
                    popExitTransition = { fadeOut() } // Exemplo de animação ao sair da tela
                ) {
                    BooksScreen(navController)
                }
                composable(Screen.Reviews.route) { ReviewsScreen(navController) }
                composable(Screen.Lists.route) { ListScreen(navController) }
                composable(Screen.Search.route) { SearchBookScreen(navController) }
                composable(Screen.Profile.route) { ProfileScreen(navController) }
            }
        }

        // CustomBottomNavigation para alternar entre Home, Search e Profile
        CustomBottomNavigation(
            selectedTab = uiState.selectedBottomNavIndex,
            onTabSelected = { index ->
                viewModel.onBottomNavItemSelected(index)
                navController.navigate(
                    when (index) {
                        0 -> Screen.Books.route
                        1 -> Screen.Search.route
                        2 -> Screen.Profile.route
                        else -> Screen.Books.route
                    }
                ) {
                    // Garante que a tela de `Search` e `Profile` também restaure o estado ao navegar
                    restoreState = true
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
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
