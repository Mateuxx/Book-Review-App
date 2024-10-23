package com.example.bookappreview.presentation.states

import com.example.bookappreview.presentation.model.LivroParcelable

data class ReviewScreenUiState(
    val book: LivroParcelable? = null, // our book from sharedViewModel
    val rating: Int = 0, // from 0 to 5
    val liked: Boolean = false,
    val reviewString: String = "" // text from user
)



