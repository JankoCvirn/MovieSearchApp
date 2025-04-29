package com.cvirn.moviesearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cvirn.domain.genre.MovieGenre
import com.cvirn.domain.usecase.GenreUseCaseImpl
import com.cvirn.domain.usecase.MoviesUseCaseImpl
import com.cvirn.moviesearch.ui.screen.fllter.FilterUiAction
import com.cvirn.moviesearch.ui.screen.fllter.FilterUiState
import com.cvirn.moviesearch.ui.screen.home.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SharedViewModel(
    private val genreUseCaseImpl: GenreUseCaseImpl,
    private val moviesUseCaseImpl: MoviesUseCaseImpl,
) : ViewModel() {
    private val _filterUiState = MutableStateFlow<FilterUiState>(FilterUiState.Idle)
    val filterUiState: StateFlow<FilterUiState> = _filterUiState
    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    init {
        loadingState()
        getInitialData()
    }

    fun doFilterAction(filterUiAction: FilterUiAction) {
        when (filterUiAction) {
            is FilterUiAction.SelectedGenre -> {
                loadingState()
                updateFilterUiState(
                    genre = filterUiAction.genre,
                    list = (filterUiState.value as FilterUiState.Data).genreList,
                )
                getGenreMovies(filterUiAction.genre)
            }
        }
    }

    private fun loadingState() {
        viewModelScope.launch {
            _homeUiState.update {
                HomeUiState.Loading
            }
        }
    }

    private fun updateFilterUiState(
        genre: MovieGenre,
        list: List<MovieGenre>,
    ) {
        _filterUiState.update {
            FilterUiState.Data(
                genreList = list,
                selectedGenre = genre,
            )
        }
    }

    private fun getInitialData() {
        viewModelScope.launch {
            val genreList = genreUseCaseImpl.getAllGenres()
            if (genreList.isNullOrEmpty()) {
                _homeUiState.update {
                    HomeUiState.Error
                }
            } else {
                updateFilterUiState(genre = genreList.first(), list = genreList)
                getGenreMovies(genre = genreList.first())
            }
        }
    }

    private fun getGenreMovies(genre: MovieGenre) {
        viewModelScope.launch {
            val movieList = moviesUseCaseImpl.getMoviesList(genreId = genre.id.toString())
            if (movieList.isNullOrEmpty()) {
                _homeUiState.update {
                    HomeUiState.Error
                }
            } else {
                _homeUiState.update {
                    HomeUiState.Data(
                        movieList = movieList,
                    )
                }
            }
        }
    }
}
