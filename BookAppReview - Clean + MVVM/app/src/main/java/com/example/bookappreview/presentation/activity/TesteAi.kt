package com.example.bookappreview.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bookappreview.R
import com.example.bookappreview.data.webclient.aiservice.AiService
import com.example.bookappreview.data.webclient.aiservice.ChatCompletionRequest
import com.example.bookappreview.data.webclient.aiservice.ChatCompletionResponse
import com.example.bookappreview.data.webclient.aiservice.Message
import com.example.bookappreview.data.webclient.aiservice.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TesteAi : AppCompatActivity() {

    /**
     * Only for tests
     */
    private val service = AiService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_teste_ai)
//        service.fetchBookRecommendations(a"O senhor dos Aneis")

    }
}