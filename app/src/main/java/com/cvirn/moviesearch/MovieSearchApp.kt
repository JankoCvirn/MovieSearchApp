package com.cvirn.moviesearch

import android.app.Application
import com.cvirn.data.di.dataModule
import com.cvirn.domain.di.domainModule
import com.cvirn.moviesearch.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieSearchApp)
            modules(
                dataModule,
                domainModule,
                appModule,
            )
        }
    }
}
