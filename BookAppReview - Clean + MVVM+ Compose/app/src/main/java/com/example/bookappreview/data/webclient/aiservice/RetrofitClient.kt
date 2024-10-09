package com.example.bookappreview.data.webclient.aiservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //url da api - CURL e tals
    private const val BASE_URL = "https://api.groq.com/openai/v1/chat/"

    private val retrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val groqService: GroqService by lazy {
        retrofitInstance.create(GroqService::class.java)
    }
}