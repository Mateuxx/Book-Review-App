package com.example.bookappreview.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bookappreview.R
import com.example.bookappreview.database.AppDatabase
import com.example.bookappreview.model.Livro
import com.example.bookappreview.repository.MainRepository
import com.example.bookappreview.ui.viewModel.AddLivroViewModel
import com.example.bookappreview.webclient.NetworkService
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
        val livro = intent.getParcelableExtra<Livro>("LIVRO_OBJ")
        Log.i("TAG", "carregaLivro: $livro")
    }
}