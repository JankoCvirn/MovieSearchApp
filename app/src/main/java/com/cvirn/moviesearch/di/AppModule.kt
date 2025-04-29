package com.cvirn.moviesearch.di

import com.cvirn.moviesearch.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule =
    module {
        viewModel { SharedViewModel(get(), get()) }
    }
