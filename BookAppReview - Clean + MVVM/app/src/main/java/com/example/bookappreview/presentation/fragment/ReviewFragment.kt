package com.example.bookappreview.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bookappreview.R
import com.example.bookappreview.databinding.FragmentReviewBinding
import com.example.bookappreview.di.Injection
import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.helpers.tentaCarregarImagem
import com.example.bookappreview.helpers.toast
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.presentation.viewModel.ReviewLivroViewModel
import com.example.bookappreview.presentation.viewModel.factory.ReviewLivroViewModelFactory
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: ReviewLivroViewModel by lazy {
        val salvarLivrosUsecase =
            Injection.provideSalvarLivrosUsecase(requireContext())
        val factory = ReviewLivroViewModelFactory(salvarLivrosUsecase)
        ViewModelProvider(this, factory)[ReviewLivroViewModel::class.java]
    }

    private var isFavorite: Boolean = false

    /**
     * get the data from the previous fragment
     */
    private val args by navArgs<ReviewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // Inflating o layout do Fragment
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        get the book from the reviewFragment using safeArgs
        val livro = args.livro

        livro.let {
            preencherCampos(it)
            setupListeners(it)
        }
    }

    private fun setupListeners(livroParcelable: LivroParcelable) {
        binding.favoriteButton.setOnClickListener {
            favoriteBook()
        }
        binding.textViewSave.setOnClickListener {
            saveBook(livroParcelable)
        }
    }

    private fun favoriteBook() {
        if (!binding.favoriteButton.isActivated) {
            binding.favoriteButton.apply {
                isActivated = true
                setImageResource(R.drawable.ic_heart_red)
            }
            isFavorite = true
            binding.likedTextView.text = "Liked"
        } else {
            binding.favoriteButton.apply {
                isActivated = false
                setImageResource(R.drawable.heart_selector)
            }
            binding.likedTextView.text = "Like"
            isFavorite = false
        }
    }

    private fun saveBook(book: LivroParcelable) {
        val savingBook = Livro(
            title = book.title,
            subtitle = book.subtitle,
            publisher = book.publisher,
            imagem = book.imagem,
            description = book.description,
            pageCount = book.pageCount,
            year = book.year,
            autor = book.autor,
            genero = book.genero,
            rated = binding.ratingBar.rating.toInt(),
            review = binding.editTextTextMultiLine2.text.toString(),
            dateReview = currentDate(),
            like = isFavorite
        )
        Log.i("TAG", "onViewCreated: Vendo dados: $savingBook")
        lifecycleScope.launch {
            viewModel.saveBook(savingBook)
            requireContext().toast("Livro Salvo Com sucesso")
        }
        Toast.makeText(requireContext(), "salcvo ", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_reviewFragment_to_homeFragment)
    }

    private fun preencherCampos(livroParcelable: LivroParcelable) {
        binding.apply {
            bookImageView.tentaCarregarImagem(livroParcelable.imagem)
            bookTitleTextView.text = livroParcelable.title.toString()
            val bookYear = livroParcelable.year?.split("-")
            bookYearTextView.text = bookYear?.get(0).toString()
            dateTextView.text = currentDate()
        }
    }

    @SuppressLint("NewApi")
    private fun currentDate(): String {
        val monthNames = mapOf(
            1 to "Janeiro", 2 to "Fevereiro", 3 to "Março", 4 to "Abril",
            5 to "Maio", 6 to "Junho", 7 to "Julho", 8 to "Agosto",
            9 to "Setembro", 10 to "Outubro", 11 to "Novembro", 12 to "Dezembro"
        )
        val calendar = Calendar.getInstance()
        val monthNumber = calendar.get(Calendar.MONTH) + 1
        val monthName = monthNames[monthNumber]
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)
        val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time)
        return "$dayOfWeek, $day $monthName $year"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}