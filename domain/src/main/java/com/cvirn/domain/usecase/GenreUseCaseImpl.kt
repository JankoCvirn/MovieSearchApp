package com.cvirn.domain.usecase

import com.cvirn.data.repository.TmdbResoritory
import com.cvirn.data.response.ApiResult
import com.cvirn.domain.constant.NetworkRules
import com.cvirn.domain.genre.MovieGenre
import kotlinx.coroutines.delay

class GenreUseCaseImpl(
    private val tmdbResoritory: TmdbResoritory,
) : GenreUseCase {
    override suspend fun getAllGenres(): List<MovieGenre>? {
        repeat(NetworkRules.RETRY_COUNT) { attempt ->
            when (val apiResult = tmdbResoritory.getGenres()) {
                is ApiResult.Success -> {
                    return apiResult.data.genres.map {
                        MovieGenre(id = it.id, name = it.name)
                    }
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
}
