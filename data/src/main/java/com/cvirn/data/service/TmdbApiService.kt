package com.cvirn.data.service

import com.cvirn.data.response.genre.GenreResponse
import com.cvirn.data.response.movie.MovieDetailsResponse
import com.cvirn.data.response.movies.MoviesByGenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {
    @GET("discover/movie")
    suspend fun getMoviesByGenreId(
        @Query("with_genres") genreId: String,
        @Query("api_key") apiKey: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1",
        @Query("sort_by") sortBy: String = "popularity.desc",
    ): Response<MoviesByGenreResponse>

    @GET("genre/movie/list")
    suspend fun getGenresList(
        @Query("api_key") apiKey: String,
    ): Response<GenreResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String,
    ): Response<MovieDetailsResponse>
}
