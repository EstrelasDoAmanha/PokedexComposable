package com.example.pokedexcompose.app.initializer.koin.modules

import org.koin.core.KoinApplication
import org.koin.core.module.Module

interface KoinModules {
    val features: List<Module>
    val libraries: List<Module>
    val cache: List<Module>

    fun KoinApplication.load()
}
