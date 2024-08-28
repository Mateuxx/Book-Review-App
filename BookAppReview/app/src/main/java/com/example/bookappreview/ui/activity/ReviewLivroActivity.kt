package com.example.bookappreview.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bookappreview.databinding.ActivityReviewLivroBinding
import com.example.bookappreview.helpers.tentaCarregarImagem
import com.example.bookappreview.model.Livro
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReviewLivroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityReviewLivroBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val livro = intent.getParcelableExtra<Livro>("LIVRO_OBJ")
        Log.i("TAG", "onCreate: Livro Carregado: $livro")
        preencherCampos(livro!!)

    }

    private fun preencherCampos(livro: Livro) {
        binding.apply {
            bookImageView.tentaCarregarImagem(livro.imagem)
            bookTitleTextView.text = livro.title.toString()
            val bookYear = livro.year?.split("-")
            bookYearTextView.text = bookYear?.get(0).toString()
            val date  = currentDate()
            dateTextView.text = date
        }
    }

    @SuppressLint("NewApi")
    private fun currentDate (): String{
        val monthNames = mapOf(
            1 to "Janeiro",
            2 to "Fevereiro",
            3 to "Março",
            4 to "Abril",
            5 to "Maio",
            6 to "Junho",
            7 to "Julho",
            8 to "Agosto",
            9 to "Setembro",
            10 to "Outubro",
            11 to "Novembro",
            12 to "Dezembro"
        )
        val calendar = Calendar.getInstance()
        val monthNumber = calendar.get(Calendar.MONTH) + 1
        val monthName = monthNames[monthNumber]
        val day =  calendar.get(Calendar.DAY_OF_MONTH)
        val year  = calendar.get(Calendar.YEAR)
        val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time)
        val finalString =  "$dayOfWeek, $day $monthName $year"
        Log.i("TAG", "currentDate: Data: $finalString")
        return  finalString
    }
}