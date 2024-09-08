package com.example.pokedexcompose.app

import android.app.Application
import android.content.Context
import androidx.startup.AppInitializer

class PokedexComposeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeAppDependencies()
    }

    private fun Context.initializeAppDependencies() {
        AppInitializer
            .getInstance(this)
            .initializeComponent(
                com.example.pokedexcompose.app.initializer.AppInitializer::class.java
            )
    }
}
