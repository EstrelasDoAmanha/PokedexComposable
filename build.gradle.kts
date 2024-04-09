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
    jacoco
    java
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        html.outputLocation = layout.buildDirectory.dir("jacoco_html")
    }
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "jacoco")
    apply(plugin = "java-library")
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

    jacoco {
        toolVersion = "0.8.11"
        // Use Default reports/jacoco
//        reportsDirectory = layout.buildDirectory.dir("")
    }

    tasks.withType<Test>() {
        finalizedBy("jacocoTestReport")
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.5".toBigDecimal()
            }
        }
    }
}

private val SourceSetContainer.srcDirs: Set<File>
    get() = sourceSets.main.get().allSource.srcDirs

private val SourceSetContainer.output: SourceSetOutput
    get() = sourceSets.main.get().output

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    additionalSourceDirs.setFrom(sourceSets.srcDirs)
    sourceDirectories.setFrom(sourceSets.srcDirs)
    classDirectories.setFrom(sourceSets.output)
    reports {
        xml.required = true
        html.outputLocation = layout.buildDirectory.dir("jacoco_html")
    }
}
