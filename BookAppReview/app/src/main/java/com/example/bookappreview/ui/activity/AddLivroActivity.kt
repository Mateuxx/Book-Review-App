package com.example.bookappreview.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookappreview.R
import org.json.JSONObject

class AddLivroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_livro)

        val mRequestQueue = Volley.newRequestQueue(this@AddLivroActivity)

        mRequestQueue.cache.clear()

        val searchQuery: String? = "Python"

        val url = "https://www.googleapis.com/books/v1/volumes?q=$searchQuery"

        val queue = Volley.newRequestQueue(this@AddLivroActivity)

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