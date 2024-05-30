import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    alias(deps.plugins.androidApplication)
    alias(deps.plugins.kotlinAndroid)
    alias(deps.plugins.kotlinSerialization)
    alias(deps.plugins.ktlint)
}

android {
    namespace = deps.versions.namespace.get()
    compileSdk = deps.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = deps.versions.applicationId.get()
        minSdk = deps.versions.minSdk.get().toInt()
        targetSdk = deps.versions.targetSdk.get().toInt()
        versionCode = deps.versions.versionCode.get().toInt()
        versionName = deps.versions.versionName.get()

        testInstrumentationRunner = deps.versions.testInstrumentationRunner.get()
        vectorDrawables {
            useSupportLibrary = deps.versions.useSupportLibrary.get().toBoolean()
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = deps.versions.releaseMinify.get().toBoolean()
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = deps.versions.jvmTarget.get()
    }
    buildFeatures {
        compose = deps.versions.composeProject.get().toBoolean()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = deps.versions.kotlinCompilerExtVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(deps.coreKtx)
    implementation(deps.lifecycleRuntimeKtx)
    implementation(deps.activityCompose)

    implementation(platform(deps.compose.bom))
    implementation(deps.compose.ui)
    implementation(deps.compose.ui.graphics)
    implementation(deps.compose.ui.tooling.preview)
    implementation(deps.compose.material3)

    implementation(deps.navigationCompose)

    implementation(deps.io.coil.kt.coil.compose)
    implementation(deps.io.coil.kt.coil.gif)
    implementation(deps.io.coil.kt.coil.svg)

    implementation(platform(deps.koin.bom))
    implementation(deps.koin.compose)
    implementation(deps.koin.android)
    implementation(deps.koin.coroutines)
    implementation(deps.koin.navigation)

    implementation(deps.ktor.client.core)
    implementation(deps.ktor.serialization)
    implementation(deps.material.icons.extended)

    implementation(deps.pagingCompose)
    implementation(deps.pagingRuntime)
    implementation(deps.appStartup)

    debugImplementation(deps.leakcanary)

    testImplementation(deps.junit)
    androidTestImplementation(deps.junitExt)
    androidTestImplementation(deps.espressoCore)
    androidTestImplementation(platform(deps.compose.bom))
    androidTestImplementation(deps.junitUiTest4)
    debugImplementation(deps.compose.ui.tooling)
    debugImplementation(deps.compose.ui.test.manifest)

    implementation(project(":network"))
    implementation(project(":designsystem"))
    implementation(project(":features:home:public"))
    implementation(project(":features:home:implementation"))
    implementation(project(":core:coreandroid"))
    implementation(project(":core:corekotlin"))
    implementation(project(":features:details:public"))
    implementation(project(":features:details:implementation"))
    implementation(project(":flipper"))
}

tasks.register<Copy>("installPreCommitHook") {
    description = "Copy pre-commit git hook from the scripts folder to the .git/hooks folder."
    group = "git hooks"
    outputs.upToDateWhen { false }
    from("$rootDir/scripts/githooks/pre-commit") {
        this.fileMode = 777
    }
    into("$rootDir/.git/hooks/").fileMode = 777

    doLast {
        println("⚈ ⚈ ⚈ Adding permissions to Pre Commit Git Hook Script on Build ⚈ ⚈ ⚈")
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            println("✅ its windows")
        } else {
            println("✅ its linux - Permissions added to Pre Commit Git Hook Script.")
            exec {
                commandLine("chmod", "+x", "$rootDir/.git/hooks/pre-commit")
            }
            println("✅ Permissions added to Pre Commit Git Hook Script.")
        }
    }
    fileMode = 777
}

project.tasks.preBuild.dependsOn("installPreCommitHook")
