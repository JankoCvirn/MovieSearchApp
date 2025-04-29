package com.cvirn.data.repository

import com.cvirn.data.response.ApiResult
import com.cvirn.data.response.genre.GenreResponse
import com.cvirn.data.response.movie.MovieDetailsResponse
import com.cvirn.data.response.movies.MoviesByGenreResponse
import com.cvirn.data.response.toApiResult
import com.cvirn.data.service.ApiKeyProvider
import com.cvirn.data.service.TmdbApiService

class TmdbResoritory(
    private val tmdbApiService: TmdbApiService,
    private val apiKeyProvider: ApiKeyProvider,
) {
    suspend fun getGenres(): ApiResult<GenreResponse> =
        try {
            tmdbApiService
                .getGenresList(
                    apiKey = apiKeyProvider.get(),
                ).toApiResult()
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "")
        }

    suspend fun getMoviesByGenre(genreId: String): ApiResult<MoviesByGenreResponse> =
        try {
            tmdbApiService
                .getMoviesByGenreId(
                    genreId = genreId,
                    apiKey = apiKeyProvider.get(),
                ).toApiResult()
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "")
        }

    suspend fun getMovieDetails(movieId: String): ApiResult<MovieDetailsResponse> =
        try {
            tmdbApiService
                .getMovieDetail(
                    movieId = movieId,
                    apiKey = apiKeyProvider.get(),
                ).toApiResult()
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "")
        }
}
