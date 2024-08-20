package com.example.bookappreview.ui.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.bookappreview.model.Livro
import com.example.bookappreview.model.Usuario
import com.example.bookappreview.repository.MainRepository
import java.util.ArrayList

class AddLivroViewModel(
    private val repository: MainRepository
) : ViewModel() {

      fun buscaLivro(key: String, context: Context){
         repository.buscaLivros(key, context)
    }

}