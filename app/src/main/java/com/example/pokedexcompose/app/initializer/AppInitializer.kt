package com.example.pokedexcompose.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.example.pokedexcompose.app.initializer.koin.KoinInitializer

class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) { /*Do Nothing*/ }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> =
        mutableListOf(KoinInitializer::class.java)
}
