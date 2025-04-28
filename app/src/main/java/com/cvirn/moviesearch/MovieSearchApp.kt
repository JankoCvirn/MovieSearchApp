package com.cvirn.moviesearch

import android.app.Application
import com.cvirn.data.dataModule
import com.cvirn.domain.domainModule
import com.cvirn.moviesearch.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieSearchApp)
            modules(
                appModule,
                dataModule,
                domainModule,
            )
        }
    }
}
