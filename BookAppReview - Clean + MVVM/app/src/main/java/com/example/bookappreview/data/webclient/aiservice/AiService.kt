package com.example.bookappreview.data.webclient.aiservice

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Service for ai recommendation using llm
 */
class AiService {

    private  var result: String? = null

    /**
     * Makes the Request with favorites books from the user
     */
    fun fetchBookRecommendations(book: String): String? {
        val request = ChatCompletionRequest(
            messages = listOf(
                Message(
                    role = "user",
                    content = "Me devolva recomendações de livros com base em $book no formato de" +
                            " lista apenas com os títulos"
                )
            ),
            model = "llama3-8b-8192"
        )

        RetrofitClient.groqService.getBookRecommendations(request)
            .enqueue(object : Callback<ChatCompletionResponse> {
                override fun onResponse(
                    call: Call<ChatCompletionResponse>,
                    response: Response<ChatCompletionResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.i("TAG", "onResponse: Deu certo a resposta")
                        val iaResponse =
                            response.body()?.choices?.firstOrNull()?.message?.content?.trim() ?: ""
                        Log.i("TAG", "onResponse: Response: $iaResponse")
                        val parsedList = iaResponse.split(Regex("\\n\\s*"))
                            .filterNot { it.matches(Regex("^[0-9]+\\..*")) }
                        result = parsedList.joinToString(separator = "\n")
                        Log.d("TAG", "onResponse: Resposta :  $result")
                    } else {
                        result = "Error: ${response.code()}"
                        Log.i("TAG", "onResponse: Requisicao falhou")
                    }
                }

                override fun onFailure(call: Call<ChatCompletionResponse>, t: Throwable) {
                    println("Failure: ${t.message}")
                }
            })
        return result
    }
}