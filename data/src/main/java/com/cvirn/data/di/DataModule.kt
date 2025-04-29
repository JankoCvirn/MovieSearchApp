package com.cvirn.data.di

import com.cvirn.data.repository.TmdbResoritory
import com.cvirn.data.service.ApiKeyProvider
import com.cvirn.data.service.AuthInterceptor
import com.cvirn.data.service.TmdbApiService
import com.cvirn.data.service.TokenProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule =
    module {

        single {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        single {
            OkHttpClient
                .Builder()
                .addInterceptor(get<HttpLoggingInterceptor>())
                .addInterceptor(get<AuthInterceptor>())
                .callTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        single {
            Retrofit
                .Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
        }

        single { get<Retrofit>().create(TmdbApiService::class.java) }

        single { TokenProvider() }
        single { ApiKeyProvider() }
        single { AuthInterceptor(get()) }
        single { TmdbResoritory(get(), get()) }
    }
