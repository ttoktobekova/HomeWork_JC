package com.example.homework_jc.app

import android.app.Application
import com.example.homework_jc.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickAndMortyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickAndMortyApplication)
            modules(AppModule.module)
        }
    }
}