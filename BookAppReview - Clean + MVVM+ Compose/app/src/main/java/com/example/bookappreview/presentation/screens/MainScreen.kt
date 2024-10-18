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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.bookappreview.presentation.components.CustomBottomNavigation
import com.example.bookappreview.presentation.components.CustomTabRow
import com.example.bookappreview.presentation.navigation.NavGraph
import com.example.bookappreview.presentation.navigation.handlers.handleBottomNavigation
import com.example.bookappreview.presentation.navigation.handlers.handleTabNavigation
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel
import com.example.bookappreview.presentation.viewModel.MainViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: BookSharedViewModel,
    viewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {


        // Renderiza o CustomTabRow se a aba inferior for "Home"
        if (uiState.selectedBottomNavIndex == 0) {
            // Renderiza o Título
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
        // Renderiza o Titulo do app em profile as well
        if (uiState.selectedBottomNavIndex == 2) {
            // Renderiza o Título
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

        // Usa o NavGraph para a navegação centralizada
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            NavGraph(sharedViewModel = sharedViewModel, navController = navController)
        }

        // CustomBottomNavigation para alternar entre Home, Search e Profile
        CustomBottomNavigation(
            selectedTab = uiState.selectedBottomNavIndex,
            onTabSelected = { index ->
                viewModel.onBottomNavItemSelected(index)
                handleBottomNavigation(
                    navController,
                    index
                )  // Navegação movida para função separada
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
