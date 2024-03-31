package com.example.pokedexcompose.app.initializer.koin

import android.content.Context
import androidx.startup.Initializer
import com.example.pokedexcompose.app.initializer.koin.modules.AppKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<KoinApplication> {

    private val koinModules = AppKoinModules()

    override fun create(context: Context): KoinApplication = startKoin {
        androidContext(context)
        with(koinModules) { load() }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
