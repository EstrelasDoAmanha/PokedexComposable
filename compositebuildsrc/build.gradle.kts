plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
    gradlePluginPortal()
}

gradlePlugin {
    val pluginName = "class-loader-plugin"
    val implementationClassName = "plugins.ClassLoaderPlugin"
    plugins.register(pluginName) {
        id = pluginName
        implementationClass = implementationClassName
    }
}

kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}