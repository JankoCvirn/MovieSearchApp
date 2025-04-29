package com.cvirn.moviesearch.ui.screen.home

import com.cvirn.domain.movie.Movie

sealed class HomeUiState {
    object Idle : HomeUiState()

    object Error : HomeUiState()

    object Loading : HomeUiState()

    data class Data(
        val movieList: List<Movie>,
    ) : HomeUiState()
}
