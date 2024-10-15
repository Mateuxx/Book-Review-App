package com.example.bookappreview.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.navigation.compose.rememberNavController
import com.example.bookappreview.presentation.screens.MainScreen
import com.example.bookappreview.presentation.viewModel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MainScreen(viewModel = MainViewModel())
        }
    }

}