package com.cvirn.moviesearch.ui.screen.fllter

import com.cvirn.domain.genre.MovieGenre

sealed class FilterUiAction {
    data class SelectedGenre(
        val genre: MovieGenre,
    ) : FilterUiAction()
}
