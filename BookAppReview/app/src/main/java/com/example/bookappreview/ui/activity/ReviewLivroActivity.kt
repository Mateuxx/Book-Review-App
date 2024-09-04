package com.example.bookappreview.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bookappreview.R
import com.example.bookappreview.database.AppDatabase
import com.example.bookappreview.databinding.ActivityReviewLivroBinding
import com.example.bookappreview.helpers.tentaCarregarImagem
import com.example.bookappreview.helpers.toast
import com.example.bookappreview.helpers.vaiPara
import com.example.bookappreview.model.Livro
import com.example.bookappreview.model.LivroSalvo
import com.example.bookappreview.repository.LivroRepository
import com.example.bookappreview.repository.UsuarioRepository
import com.example.bookappreview.ui.viewModel.AddLivroViewModel
import com.example.bookappreview.ui.viewModel.ReviewLivroViewModel
import com.example.bookappreview.webclient.NetworkService
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReviewLivroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityReviewLivroBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        val repository = LivroRepository(AppDatabase.instancia(this).livroSalvodao())
        ReviewLivroViewModel(repository)
    }

    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val livro = intent.getParcelableExtra<Livro>("LIVRO_OBJ")
        Log.i("TAG", "onCreate: Livro Carregado: $livro")
        preencherCampos(livro!!)

        binding.favoriteButton.setOnClickListener {
            favoriteBook()
        }
        binding.textViewSave.setOnClickListener {
            saveBook(livro)
        }
    }

    private fun favoriteBook() {
        if (!binding.favoriteButton.isActivated) {
            binding.favoriteButton.apply {
                Log.i("TAG", "favoriteBook: isActive ao clicar: $isActivated")
                isActivated = true
                setImageResource(R.drawable.ic_heart_red)
            }
            isFavorite = true
            binding.likedTextView.text = "Liked"
        } else {
            binding.favoriteButton.apply {
                Log.i("TAG", "favoriteBook: isActive ao clicar: $isActivated")
                isActivated = false
                setImageResource(R.drawable.heart_selector)
            }
            binding.likedTextView.text = "Like"
            isFavorite = false
        }
    }

    private fun saveBook(book: Livro) {
        val savingBook = LivroSalvo(
            id = book.id,
            livro = book,
            like = isFavorite,
            rated = binding.ratingBar.rating.toInt(),
            review = binding.editTextTextMultiLine2.text.toString(),
            dateReview = currentDate()
        )
        Log.i("TAG", "onCreate: Vendo dados: ${savingBook.livro.id}")
        lifecycleScope.launch {
            viewModel.saveBook(savingBook)
            toast("Livro Salvo Com sucesso")
        }
        vaiPara(HomeActivity::class.java)
    }


    private fun preencherCampos(livro: Livro) {
        binding.apply {
            bookImageView.tentaCarregarImagem(livro.imagem)
            bookTitleTextView.text = livro.title.toString()
            val bookYear = livro.year?.split("-")
            bookYearTextView.text = bookYear?.get(0).toString()
            val date = currentDate()
            dateTextView.text = date
        }
    }

    /**
     * Get the current phones current date
     */
    @SuppressLint("NewApi")
    private fun currentDate(): String {
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
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)
        val dayOfWeek = SimpleDateFormat(
            "EEEE",
            Locale.getDefault()
        ).format(calendar.time)
        val finalString = "$dayOfWeek, $day $monthName $year"
        Log.i("TAG", "currentDate: Data: $finalString")
        return finalString
    }
}