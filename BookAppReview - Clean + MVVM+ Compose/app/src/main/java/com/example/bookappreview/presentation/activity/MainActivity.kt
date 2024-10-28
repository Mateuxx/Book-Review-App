package com.example.bookappreview.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bookappreview.presentation.screens.MainScreen
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Criando a instância do SharedViewModel dentro da Activity
            val sharedViewModel: BookSharedViewModel = hiltViewModel()
            //  SharedViewModel para o NavGraph
            MainScreen(sharedViewModel = sharedViewModel)
        }
    }
}