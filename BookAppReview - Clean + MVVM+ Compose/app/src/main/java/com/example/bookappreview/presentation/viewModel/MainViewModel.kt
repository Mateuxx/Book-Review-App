package com.example.bookappreview.presentation.viewModel



import androidx.lifecycle.ViewModel
import com.example.bookappreview.presentation.states.MainScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    // Estado inicial da tela
    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    val tabs = listOf("Books", "Reviews", "Lists")

    // Atualiza o estado da aba inferior selecionada (Home, Search, Profile)
    fun onBottomNavItemSelected(index: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedBottomNavIndex = index
            )
        }
    }

    // Atualiza o estado da aba superior (Books, Reviews, Lists)
    fun onTabSelected(index: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedTabIndex = index
            )
        }
    }
}