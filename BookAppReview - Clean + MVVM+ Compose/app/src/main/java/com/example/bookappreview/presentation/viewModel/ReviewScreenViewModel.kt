package com.example.bookappreview.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookappreview.domain.model.Livro
import com.example.bookappreview.domain.usecase.livro.SalvarLivrosUsecase
import com.example.bookappreview.presentation.model.LivroParcelable
import com.example.bookappreview.presentation.states.ReviewScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ReviewScreenViewModel @Inject constructor(
    private val salvarLivrosUsecase: SalvarLivrosUsecase
) : ViewModel() {

    //Encapsulamento do estado da ui
    private val _uiState =
        MutableStateFlow(ReviewScreenUiState(date = currentDate())) // Inicia com a data atual
    val uiState: StateFlow<ReviewScreenUiState> = _uiState.asStateFlow()

    private val _saveCompleteEvent = MutableStateFlow(false)
    val saveCompleteEvent: StateFlow<Boolean> = _saveCompleteEvent

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

    fun saveBook() {
        val livro = _uiState.value.book ?: return // Retorna se livro for nulo - nao faz nada

        // Cria um objeto Livro com base no estado atual da UI
        val savingBook = Livro(
            title = livro.title,
            subtitle = livro.subtitle,
            publisher = livro.publisher,
            imagem = livro.imagem,
            description = livro.description,
            pageCount = livro.pageCount,
            year = livro.year,
            autor = livro.autor,
            genero = livro.genero,
            rated = _uiState.value.rating,
            review = _uiState.value.reviewString,
            dateReview = _uiState.value.date,
            like = _uiState.value.liked
        )
        //Lança uma corrotina para salvar o livro no banco de dados
        viewModelScope.launch {
            salvarLivrosUsecase(savingBook)
            // Emite o evento de conclusão
            _saveCompleteEvent.value = true
        }

    }

    // Função para resetar o evento após a navegação
    fun resetSaveComplete() {
        _saveCompleteEvent.value = false // Reseta o evento para evitar navegações repetidas
    }


    // TODO: Refatorar para isso vir de um outro lugar - Clean
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