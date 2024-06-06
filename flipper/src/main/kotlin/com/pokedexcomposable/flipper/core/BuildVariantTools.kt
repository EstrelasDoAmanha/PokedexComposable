package com.pokedexcomposable.flipper.core

import android.content.Context
import android.content.pm.ApplicationInfo

/**
 * Function to check app's global flag for debug build
 *
 * This function just check, in a safely manner, if app's build is a debug build.
 *
 * @author https://medium.com/mobile-app-development-publication/checking-debug-build-the-right-way-d12da1098120
 */
val Context.debug: Boolean
    get() {
        val flags = applicationInfo.flags
        val debuggable = ApplicationInfo.FLAG_DEBUGGABLE

        return (flags and debuggable) != 0
    }
