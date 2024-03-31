package com.example.pokedexcompose.app.initializer.koin.modules

import com.example.pokedexcompose.ui.list.di.dataSource
import com.example.pokedexcompose.ui.list.di.viewModel
import com.pokedexcompose.network.di.networkModule
import org.koin.core.KoinApplication
import org.koin.core.module.Module

class AppKoinModules : KoinModules {
    override val features: List<Module>
        get() = listOf(
            viewModel,
            dataSource
        )
    override val libraries: List<Module>
        get() = listOf(
            networkModule
        )
    override val cache: List<Module>
        get() = listOf()

    override fun KoinApplication.load() {
        this.modules(features + libraries + cache)
    }
}
