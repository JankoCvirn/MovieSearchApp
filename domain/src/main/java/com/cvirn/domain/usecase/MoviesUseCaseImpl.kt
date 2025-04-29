package com.cvirn.domain.usecase

import com.cvirn.data.repository.TmdbResoritory
import com.cvirn.data.response.ApiResult
import com.cvirn.data.response.movies.Result
import com.cvirn.domain.constant.NetworkRules
import com.cvirn.domain.movie.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class MoviesUseCaseImpl(
    private val tmdbResoritory: TmdbResoritory,
) : MoviesUseCase {
    override suspend fun getMoviesList(genreId: String): List<Movie>? {
        val movieList = getMoviesListByGenre(genreId = genreId)
        return if (movieList.isNullOrEmpty()) {
            null
        } else {
            getMovieDetailsList(movieList)
        }
    }

    private suspend fun getMoviesListByGenre(genreId: String): List<Result>? {
        repeat(NetworkRules.RETRY_COUNT) { attempt ->
            when (val apiResult = tmdbResoritory.getMoviesByGenre(genreId = genreId)) {
                is ApiResult.Success -> {
                    return apiResult.data.results
                }

                is ApiResult.Error -> {
                    if (attempt == 2) {
                        return null
                    }
                    delay(NetworkRules.RETRY_DELAY)
                }
            }
        }
        return null
    }

    private suspend fun getMovieDetailsList(movieList: List<Result>): List<Movie> =
        coroutineScope {
            val detailedDeferred =
                movieList.map { movie ->
                    async(Dispatchers.IO) {
                        when (
                            val movieDetails =
                                tmdbResoritory.getMovieDetails(movieId = movie.id.toString())
                        ) {
                            is ApiResult.Success -> {
                                Movie(
                                    title = movie.title,
                                    image = movie.posterPath,
                                    rating = movie.voteAverage,
                                    budget = movieDetails.data.budget,
                                    revenue = movieDetails.data.revenue,
                                )
                            }

                            is ApiResult.Error -> {
                                null
                            }
                        }
                    }
                }
            detailedDeferred.awaitAll().filterNotNull()
        }
}
