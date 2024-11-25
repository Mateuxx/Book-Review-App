package com.example.bookappreview.presentation.screens


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.bookappreview.presentation.components.CustomBottomNavigation
import com.example.bookappreview.presentation.components.CustomTabRow
import com.example.bookappreview.presentation.navigation.NavGraph
import com.example.bookappreview.presentation.navigation.Screen
import com.example.bookappreview.presentation.navigation.handlers.handleBottomNavigation
import com.example.bookappreview.presentation.navigation.handlers.handleTabNavigation
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel
import com.example.bookappreview.presentation.viewModel.BooksScreenViewModel
import com.example.bookappreview.presentation.viewModel.MainViewModel
import com.example.bookappreview.presentation.viewModel.ReviewScreenViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: BookSharedViewModel,
    viewModel: MainViewModel = viewModel(),
    reviewViewModel: ReviewScreenViewModel
) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()
    val bookScreenViewModel: BooksScreenViewModel = hiltViewModel()

    // Observa o evento de salvamento completo do ReviewScreenViewModel
    val saveComplete by reviewViewModel.saveCompleteEvent.collectAsState()

    LaunchedEffect(saveComplete) {
        if (saveComplete) {
            Log.d("MainScreen", "Navigating to BooksScreen due to saveCompleteEvent")
            viewModel.setIsLoading(false)
            viewModel.onBottomNavItemSelected(0)
            navController.navigate(Screen.Books.route) {
                popUpTo(Screen.Books.route) { inclusive = true }
                launchSingleTop = true
            }
            reviewViewModel.resetSaveComplete() // Reset to prevent re-triggering
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                when (uiState.selectedBottomNavIndex) {
                    1 -> Color(0xFF181b20)
                    else -> Color.Black
                }
            )
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Renderiza o título, dependendo da aba selecionada
            if (uiState.selectedBottomNavIndex == 0) {
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
                CustomTabRow(
                    tabs = viewModel.tabs,
                    selectedTabIndex = uiState.selectedTabIndex,
                    onTabSelected = { index ->
                        viewModel.onTabSelected(index)
                        handleTabNavigation(
                            navController,
                            index
                        )
                    }
                )
            }

            // Renderiza o Título na aba de Perfil
            if (uiState.selectedBottomNavIndex == 2) {
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
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Renderiza o conteúdo principal da tela
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                NavGraph(
                    sharedViewModel = sharedViewModel,
                    navController = navController,
                    reviewModel = reviewViewModel,
                    isLoadingBooks = uiState.isLoadingBooks,
                    bookScreenViewModel = bookScreenViewModel
                )
            }

            // Navegação inferior com fundo dinâmico
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        when (uiState.selectedBottomNavIndex) {
                            1 -> Color.Gray.copy(alpha = 0.1f) // Fundo mais claro para a navegação inferior na aba de busca
                            else -> Color.Black // Fundo padrão
                        }
                    )
                    .windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                CustomBottomNavigation(
                    selectedTab = uiState.selectedBottomNavIndex,
                    onTabSelected = { index ->
                        viewModel.onBottomNavItemSelected(index)
                        handleBottomNavigation(
                            navController,
                            index
                        )
                    }
                )
            }
        }
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
