import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(deps.plugins.androidApplication) apply false
    alias(deps.plugins.androidLibrary) apply false
    alias(deps.plugins.kotlinAndroid) apply false
    alias(deps.plugins.kotlinJvm) apply false
    alias(deps.plugins.kotlinSerialization) apply false
    alias(deps.plugins.ktlint) apply false
    alias(deps.plugins.dokka) apply false
    alias(deps.plugins.detekt) apply false
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
    apply { plugin("io.gitlab.arturbosch.detekt") }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = deps.versions.jvmTarget.get()
        parallel = true
        autoCorrect = true

        reports {
            html.required = true
            xml.required = true
        }

        version = deps.versions.detekt.get()

        include("**/*.kt", "**/*.java", "**/*.kts")
        exclude("**/resources/**", "**/build/**")

        baseline.set(project.file("$rootDir/scripts/detekt/baseline/detekt-baseline.xml"))
        config.setFrom(project.file("$rootDir/scripts/detekt/config/detekt-config.yml"))
    }

    tasks.withType<DetektCreateBaselineTask>().configureEach {
        jvmTarget = deps.versions.jvmTarget.get()
    }
}
