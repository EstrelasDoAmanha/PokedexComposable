plugins {
    alias(deps.plugins.androidApplication)
    alias(deps.plugins.kotlinAndroid)
    alias(deps.plugins.kotlinSerialization)
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
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
    implementation(platform(deps.composeBom))
    implementation(deps.composeUi)
    implementation(deps.composeUiGraphics)
    implementation(deps.composeUiToolingPreview)
    implementation(deps.composeMaterial3)
    implementation(deps.navigationCompose)
    implementation(deps.io.coil.kt.coil.compose)
    implementation(platform(deps.koin.bom))
    implementation(deps.koin.compose)
    implementation(deps.koin.android)
    implementation(deps.koin.coroutines)
    implementation(deps.koin.navigation)

    testImplementation(deps.junit)
    androidTestImplementation(deps.junitExt)
    androidTestImplementation(deps.espressoCore)
    androidTestImplementation(platform(deps.composeBom))
    androidTestImplementation(deps.junitUiTest4)

    debugImplementation(deps.composeUiTooling)
    debugImplementation(deps.composeUiTestManifest)

    implementation(project(":network"))
}