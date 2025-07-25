package org.example.project

import android.app.Application
import org.example.project.di.sharedModule
import org.koin.core.context.startKoin

// Created by Zain Shakoor
// on 7/24/2025

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(sharedModule)

        }
    }
}