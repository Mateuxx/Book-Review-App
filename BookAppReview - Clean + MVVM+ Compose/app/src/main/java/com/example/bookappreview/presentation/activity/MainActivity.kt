package com.example.bookappreview.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookappreview.presentation.screens.MainScreen
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Criando a instância do SharedViewModel dentro da Activity
            val sharedViewModel= remember {
                BookSharedViewModel()
            }
            //  SharedViewModel para o NavGraph
            MainScreen(sharedViewModel = sharedViewModel)
        }
    }
}