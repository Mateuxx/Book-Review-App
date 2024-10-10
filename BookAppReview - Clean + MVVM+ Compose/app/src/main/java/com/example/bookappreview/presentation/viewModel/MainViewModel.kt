package com.example.bookappreview.presentation.viewModel



import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    // Lista de rótulos das abas
    private val _tabs = listOf("Films", "Reviews", "Lists")
    val tabs: List<String> = _tabs

    // Estado do índice da aba selecionada
    private val _selectedTabIndex = MutableStateFlow(0) // Aba inicial selecionada
    val selectedTabIndex: StateFlow<Int> = _selectedTabIndex.asStateFlow()

    // Estado do conteúdo de cada aba (pode expandir isso conforme o conteúdo necessário)
    private val _tabContent = MutableStateFlow("Tab Films selecionada") // Conteúdo inicial da aba "Films"
    val tabContent: StateFlow<String> = _tabContent.asStateFlow()

    // Função para atualizar a aba selecionada e o conteúdo correspondente
    fun onTabSelected(index: Int) {
        _selectedTabIndex.value = index

        // Atualiza o conteúdo com base na aba selecionada
        _tabContent.update {
            when (index) {
                0 -> "Tab Films selecionada"
                1 -> "Tab Reviews selecionada"
                2 -> "Tab Lists selecionada"
                else -> "Aba desconhecida"
            }
        }
    }
}