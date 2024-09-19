package com.example.bookappreview.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bookappreview.R
import com.example.bookappreview.aiservice.ChatCompletionRequest
import com.example.bookappreview.aiservice.ChatCompletionResponse
import com.example.bookappreview.aiservice.Message
import com.example.bookappreview.aiservice.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TesteAi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_teste_ai)
        fetchBookRecommendations()
    }

    private fun fetchBookRecommendations() {
        val request = ChatCompletionRequest(
            messages = listOf(
                Message(role = "user", content = "Me devolva recomendações de livros com base em Harry Potter no formato de lista apenas com os títulos")
            ),
            model = "llama3-8b-8192"
        )
        var result: String
        RetrofitClient.groqService.getBookRecommendations(request).enqueue(object : Callback<ChatCompletionResponse> {
            override fun onResponse(
                call: Call<ChatCompletionResponse>,
                response: Response<ChatCompletionResponse>
            ) {
                Log.i("TAG", "onResponse: Acho que nao chegou aqui")
                if (response.isSuccessful) {
                    Log.i("TAG", "onResponse: Deu certo a resposta")
                    val iaResponse = response.body()?.choices?.firstOrNull()?.message?.content?.trim() ?: ""
                    Log.i("TAG", "onResponse: Response: $iaResponse")
                    val parsedList = iaResponse.split(Regex("\\n\\s*")).filterNot { it.matches(Regex("^[0-9]+\\..*")) }
                   result = parsedList.joinToString(separator = "\n")
                    Log.d("TAG", "onResponse: Resposta :  $result")
                } else {
                    result = "Error: ${response.code()}"
                    Log.i("TAG", "onResponse: Requisicao falhou")
                }
            }

            override fun onFailure(call: Call<ChatCompletionResponse>, t: Throwable) {
                result = "Failure: ${t.message}"
            }
        })
    }

}