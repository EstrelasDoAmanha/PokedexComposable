plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    fun plugin(id: String, version: String) {
        implementation("$id:$id.gradle.plugin:$version")
    }

    fun dependency(module: String, version: String) {
        implementation("$module:$version")
    }

//    dependency(
//        module = "com.android.tools.build:gradle",
//        version = "8.3.1"
//    )
//    plugin("org.jetbrains.kotlin.android", "1.9.23")
//    plugin("com.android.tools.build", "8.3.1")
}
