package com.example.pokedexcompose.app

import android.app.Application
import com.example.pokedexcompose.ui.list.di.dataModule
import com.example.pokedexcompose.ui.list.di.domainModule
import com.example.pokedexcompose.ui.list.di.viewModel
import com.pokedexcomposable.flipper.core.initFlipper
import com.pokedexcompose.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokedexComposeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokedexComposeApp)
            androidLogger()
            modules(
                networkModule,
                viewModel,
                domainModule,
                dataModule
            )
        }
        initFlipper()
    }
}
