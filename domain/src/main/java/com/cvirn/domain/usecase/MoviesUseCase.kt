package com.cvirn.domain.usecase

import com.cvirn.domain.movie.Movie

interface MoviesUseCase {
    suspend fun getMoviesList(genreId: String): List<Movie>?
}
