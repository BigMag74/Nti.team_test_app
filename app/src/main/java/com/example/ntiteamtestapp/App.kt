package com.example.ntiteamtestapp

import android.app.Application
import com.example.ntiteamtestapp.di.mainScreenDatamodule
import com.example.ntiteamtestapp.di.mainScreenDomainModule
import com.example.ntiteamtestapp.di.mainScreenPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                mainScreenDatamodule,
                mainScreenPresentationModule,
                mainScreenDomainModule,
            )
        }
    }
}
