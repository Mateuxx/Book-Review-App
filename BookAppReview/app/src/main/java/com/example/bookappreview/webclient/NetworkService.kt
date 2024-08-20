package com.example.bookappreview.webclient

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookappreview.model.Livro
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONObject
import kotlin.coroutines.resume

class NetworkService {

    suspend fun bookApi(searchQuery: String, context: Context): List<Livro> {
        //faz com que seja chamado por uma courotine e cancela o mesmo
        return suspendCancellableCoroutine { continuation ->
            val mRequestQueue = Volley.newRequestQueue(context)
            mRequestQueue.cache.clear()

            val url = "https://www.googleapis.com/books/v1/volumes?q=$searchQuery"

            val request = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                { response ->
                    val arrayOfBooks = mutableListOf<Livro>()
                    val itemsArray = response.getJSONArray("items")

                    for (i in 0 until itemsArray.length()) {
                        val itemsObj = itemsArray.getJSONObject(i)
                        val volumeObj = itemsObj.getJSONObject("volumeInfo")
                        val title = volumeObj.optString("title")
                        val subtitle = volumeObj.optString("subtitle")
                        val publisher = volumeObj.optString("publisher")
                        val description = volumeObj.optString("description")
                        val pageCount = volumeObj.optInt("pageCount")
                        val imageLinks = volumeObj.optJSONObject("imageLinks")
                        val thumbnail = imageLinks?.optString("thumbnail")

                        Log.i("TAG", "bookApi: Livros: $title")

                        val livro = Livro(
                            title,
                            subtitle,
                            publisher,
                            thumbnail?:"",
                            description,
                            pageCount,
                        )

                        arrayOfBooks.add(livro)
                    }

                    continuation.resume(arrayOfBooks) // retorna o valor da chamada
                },
                { error ->
                    Log.e("TAG", "onCreate: Erro na requisição $error")
                    continuation.resume(emptyList())
                }
            )

            mRequestQueue.add(request)
        }
    }
}
