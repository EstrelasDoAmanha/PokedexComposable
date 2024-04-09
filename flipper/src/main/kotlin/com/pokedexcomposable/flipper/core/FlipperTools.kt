package com.pokedexcomposable.flipper.core

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader

fun Application.initFlipper() {
    initSoLoader()
    if (debug && FlipperUtils.shouldEnableFlipper(this)) {
        AndroidFlipperClient
            .getInstance(this)
            .apply {
                addPlugin(InspectorFlipperPlugin(
                    this@initFlipper,
                    DescriptorMapping.withDefaults()
                ))
            }.also { it.start() }
    }
}

private fun Application.initSoLoader() {
    SoLoader.init(this, false)
}