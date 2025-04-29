package com.cvirn.domain.usecase

import com.cvirn.domain.genre.MovieGenre

interface GenreUseCase {
    suspend fun getAllGenres(): List<MovieGenre>?
}
