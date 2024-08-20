package com.example.bookappreview.webclient

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class NetworkService {

     fun bookApi(searchQuery: String, context: Context){
        val mRequestQueue = Volley.newRequestQueue(context)

        mRequestQueue.cache.clear()

        val url = "https://www.googleapis.com/books/v1/volumes?q=$searchQuery"

        val queue = Volley.newRequestQueue(context)

        val jsonRequest = JSONObject()

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            jsonRequest,
            { response ->
                val itemsArray = response.getJSONArray("items")
                Log.i("TAG", "onCreate: Tamanho da requisição ${itemsArray.length()}")
                Log.i("TAG", "onCreate: $itemsArray")

                // Busca por todas as requisições encontradass
                for (i in 0 until itemsArray.length()) {
                    val itemsObj = itemsArray.getJSONObject(i)
                    val volumeObj = itemsObj.getJSONObject("volumeInfo")
                    val title = volumeObj.optString("title")
                    Log.i("TAG", "onCreate: Livro: $title")

                }
            },
            { error ->
                Log.e("TAG", "onCreate: Erro na requisição $error")
            }
        )
        queue.add(request)
    }
}