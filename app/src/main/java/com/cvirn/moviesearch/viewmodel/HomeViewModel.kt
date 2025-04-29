package com.cvirn.moviesearch.viewmodel

import androidx.lifecycle.ViewModel
import com.cvirn.domain.usecase.GenreUseCaseImpl
import com.cvirn.domain.usecase.MoviesUseCaseImpl

class HomeViewModel(
    private val genreUseCaseImpl: GenreUseCaseImpl,
    private val moviesUseCaseImpl: MoviesUseCaseImpl,
) : ViewModel() {
    init {
    }
}
