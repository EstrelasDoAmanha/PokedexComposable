package com.example.pokedexcompose.app.initializer.koin.modules

import org.koin.core.KoinApplication
import org.koin.core.module.Module

class AppKoinModules : KoinModules {
    override val features: List<Module>
        get() = listOf()
    override val libraries: List<Module>
        get() = listOf()
    override val cache: List<Module>
        get() = listOf()

    override fun KoinApplication.load() {
        this.modules(cache + features + libraries)
    }
}
