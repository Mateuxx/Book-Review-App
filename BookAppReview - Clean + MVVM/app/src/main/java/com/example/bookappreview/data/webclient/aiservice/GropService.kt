package com.example.bookappreview.data.webclient.aiservice

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GroqService {
    @Headers("Authorization: Bearer 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")
    @POST("completions")
    fun getBookRecommendations(@Body request: ChatCompletionRequest): Call<ChatCompletionResponse>
}