package com.example.bookappreview.data.webclient.aiservice

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

/**
 * Service for ai recommendation using llm
 */
class AiService {

    private  var result: String? = null

    /**
     * Makes the Request with favorites books from the user
     */
    suspend fun fetchBookRecommendations(book: String): String {
        val request = ChatCompletionRequest(
            messages = listOf(
                Message(
                    role = "user",
                    content = "Me devolva recomendações de livros com base em $book no formato de lista apenas com os títulos"
                )
            ),
            model = "llama3-8b-8192"
        )

        val response = RetrofitClient.groqService.getBookRecommendations(request).awaitResponse()

        return if (response.isSuccessful) {
            response.body()?.choices?.firstOrNull()?.message?.content?.trim() ?: ""
        } else {
            "Error: ${response.code()}"
        }
    }



    fun parseBookRecommendations(response: String): List<String> {
        val regex = Regex("\\d+\\.\\s•*")
        val books = response.split(regex)
            .filter { it.isNotBlank() } // Remove entradas vazias
            .map { it.trim() } // Remove espaços no início e fim de cada item
            .drop(1) // Remove aquela descricao de chat Aqui esta bla bla bla....
        return books
    }

}