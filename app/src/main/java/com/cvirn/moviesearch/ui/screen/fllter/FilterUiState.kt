package com.cvirn.moviesearch.ui.screen.fllter

import com.cvirn.domain.genre.MovieGenre

sealed class FilterUiState {
    object Idle : FilterUiState()

    data class Data(
        val selectedGenre: MovieGenre,
        val genreList: List<MovieGenre>,
    ) : FilterUiState()
}
