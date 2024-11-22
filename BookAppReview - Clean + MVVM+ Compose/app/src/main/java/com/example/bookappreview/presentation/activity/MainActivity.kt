package com.example.bookappreview.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bookappreview.presentation.screens.MainScreen
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookappreview.presentation.viewModel.ReviewScreenViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Criando a instância do SharedViewModel dentro da Activity
            val sharedViewModel: BookSharedViewModel = hiltViewModel()
            val reviewViewModel: ReviewScreenViewModel = hiltViewModel()

            //  SharedViewModel para o NavGraph
            MainScreen(sharedViewModel = sharedViewModel, reviewViewModel = reviewViewModel)
        }
    }
}