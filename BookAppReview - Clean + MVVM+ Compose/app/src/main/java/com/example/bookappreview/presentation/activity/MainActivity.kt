package com.example.bookappreview.presentation.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import com.example.bookappreview.presentation.screens.MainScreen
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookappreview.presentation.viewModel.ReviewScreenViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura o sistema para edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Torna a barra de status transparente e define ícones brancos
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.decorView.systemUiVisibility = (
                window.decorView.systemUiVisibility and android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                )

        setContent {
            val sharedViewModel: BookSharedViewModel = hiltViewModel()
            val reviewViewModel: ReviewScreenViewModel = hiltViewModel()

            MainScreen(sharedViewModel = sharedViewModel, reviewViewModel = reviewViewModel)
        }
    }
}
