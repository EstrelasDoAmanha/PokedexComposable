package com.pokedexcomposable.plugins


import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension

class JacocoTestReportPlugin : Plugin<Project> {

//    private val Project.android: BaseExtension
//        get() = extensions.findByName("android") as? BaseExtension
//            ?: error("Not an Android module: $name")

    private val Project.jacoco: JacocoPluginExtension
        get() = extensions.findByName("jacoco") as? JacocoPluginExtension
            ?: error("Not a Jacoco module: $name")

    override fun apply(target: Project) {
        TODO("Not yet implemented")
    }

}