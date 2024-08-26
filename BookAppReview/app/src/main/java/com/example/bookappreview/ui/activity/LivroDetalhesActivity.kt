package com.example.bookappreview.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappreview.R
import com.example.bookappreview.model.Livro
import java.util.UUID

class LivroDetalhesActivity : AppCompatActivity() {


    var livroID: UUID = UUID(0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_livro_detalhes)
        carregaLivro()
    }

    private fun carregaLivro() {
        //Refatorar depois
        val livro = intent.getParcelableExtra<Livro>("LIVRO_OBJ")
        Log.i("TAG", "carregaLivro: $livro")
    }
}