package com.ds.kotlinpagination

import android.app.Application
import com.ds.kotlinpagination.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PaginationApp : Application() {

    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    private fun configureDi() = startKoin {
        androidLogger()
        androidContext(this@PaginationApp)
        modules(appModule)
    }

}