
package com.example.bookappreview.presentation.activity

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookappreview.presentation.screens.MainScreen
import com.example.bookappreview.presentation.viewModel.BookSharedViewModel
import com.example.bookappreview.presentation.viewModel.ReviewScreenViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        private const val NOTIFICATION_PERMISSION_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura o sistema para edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Configura a barra de status transparente
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.decorView.systemUiVisibility = (
                window.decorView.systemUiVisibility and android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                )

        // Solicita permissão para notificações (se necessário)
        requestNotificationPermission()

        // Configuração do Jetpack Compose
        setContent {
            val sharedViewModel: BookSharedViewModel = hiltViewModel()
            val reviewViewModel: ReviewScreenViewModel = hiltViewModel()

            MainScreen(sharedViewModel = sharedViewModel, reviewViewModel = reviewViewModel)
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Solicita a permissão ao usuário
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_REQUEST_CODE
                )
            } else {
                Log.d("MainActivity", "Permissão de notificações já concedida.")
            }
        } else {
            Log.d("MainActivity", "Permissão de notificações não necessária para esta versão do Android.")
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == NOTIFICATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Log.d("MainActivity", "Permissão de notificações concedida pelo usuário.")
            } else {
                Log.d("MainActivity", "Permissão de notificações negada pelo usuário.")
            }
        }
    }
}


