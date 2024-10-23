package com.example.bookappreview.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.presentation.states.ReviewScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReviewScreenViewModel : ViewModel() {

    //Encapsulamento do estado da ui
    private val _uiState =
        MutableStateFlow(ReviewScreenUiState(date = currentDate())) // Inicia com a data atual
    val uiState: StateFlow<ReviewScreenUiState> = _uiState.asStateFlow()

    fun initializeBook(book: LivroParcelable) {
        _uiState.value = _uiState.value.copy(book = book)
    }

    fun updateRating(newRating: Int) {
        _uiState.value = _uiState.value.copy(rating = newRating)
    }

    fun toggleLiked() {
        _uiState.value = _uiState.value.copy(liked = !_uiState.value.liked)
    }

    fun updateReviewText(newText: String) {
        _uiState.value = _uiState.value.copy(reviewString = newText)
    }

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
}