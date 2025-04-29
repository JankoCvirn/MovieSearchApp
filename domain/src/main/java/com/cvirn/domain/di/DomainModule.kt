package com.cvirn.domain.di

import com.cvirn.domain.usecase.GenreUseCaseImpl
import com.cvirn.domain.usecase.MoviesUseCaseImpl
import org.koin.dsl.module

val domainModule =
    module {
        factory { GenreUseCaseImpl(get()) }
        factory { MoviesUseCaseImpl(get()) }
    }
