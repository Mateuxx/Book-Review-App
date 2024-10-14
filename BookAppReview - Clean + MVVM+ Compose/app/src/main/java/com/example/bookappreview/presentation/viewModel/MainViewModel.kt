package com.example.bookappreview.presentation.viewModel



import androidx.lifecycle.ViewModel
import com.example.bookappreview.presentation.states.MainScreenUiState
import com.example.bookappreview.presentation.states.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    // Estado inicial da tela
    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    val tabs = listOf("Books", "Reviews", "Lists")

    // Atualiza o estado da aba inferior selecionada
    fun onBottomNavItemSelected(index: Int) {
        _uiState.update { currentState ->
            val newScreen: Screen = when (index) {
                0 -> Screen.Books // Aba inferior "Home"
                1 -> Screen.Search // Aba inferior "Search"
                2 -> Screen.Profile // Aba inferior "Profile"
                else -> Screen.Books
            }
            currentState.copy(
                selectedBottomNavIndex = index, // Atualiza a aba inferior
                currentScreen = newScreen // Atualiza a tela atual
            )
        }
    }

    // Atualiza o estado da aba superior (TabRow) selecionada
    fun onTabSelected(index: Int) {
        _uiState.update { currentState ->
            val newScreen: Screen = when (index) {
                0 -> Screen.Books // Aba superior "Books"
                1 -> Screen.Reviews // Aba superior "Reviews"
                2 -> Screen.Lists // Aba superior "Lists"
                else -> Screen.Books
            }
            currentState.copy(
                selectedTabIndex = index, // Atualiza a aba do TabRow
                currentScreen = newScreen // Atualiza a tela atual
            )
        }
    }

    // Função para executar navegação
    fun navigateTo(screen: Screen) {
        _uiState.update { currentState ->
            currentState.copy(currentScreen = screen)
        }
    }
}