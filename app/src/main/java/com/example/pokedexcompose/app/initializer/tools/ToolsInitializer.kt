package com.example.pokedexcompose.app.initializer.tools

import android.content.Context
import androidx.startup.Initializer
import com.pokedexcomposable.flipper.core.initFlipper

class ToolsInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        context.initFlipper()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
