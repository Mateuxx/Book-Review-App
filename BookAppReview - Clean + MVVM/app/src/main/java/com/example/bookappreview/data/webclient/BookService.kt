package com.example.bookappreview.data.webclient

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookappreview.domain.model.Livro
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class BookService {

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
                        val categories = volumeObj.optString("categories")
                        val description = volumeObj.optString("description")
                        val pageCount = volumeObj.optInt("pageCount")
                        val imageLinks = volumeObj.optJSONObject("imageLinks")
                        val thumbnail = imageLinks?.optString("thumbnail")
                        val anoDeLancamento = volumeObj.optString("publishedDate")
                        val authorsArrayList: ArrayList<String> = ArrayList()
                        if (volumeObj.has("authors")) {
                            val authorsArray = volumeObj.getJSONArray("authors")
                            for (j in 0 until authorsArray.length()) {
                                authorsArrayList.add(authorsArray.optString(j))
                            }
                        } else {
                            authorsArrayList.add("Autor não encontrado.")
                        }


                        Log.i("TAG", "bookApi: $itemsObj")
                        Log.i("TAG", "bookApi: Livros: $title")


                        val livro = Livro(
                            title = title,
                            subtitle = subtitle,
                            publisher = publisher,
                            imagem = thumbnail ?: "",
                            description = description,
                            pageCount = pageCount,
                            year = anoDeLancamento,
                            autor = authorsArrayList[0],
                            genero = categories,
                            rated = 0,
                            like = false,
                            review = "",
                            dateReview = ""

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
