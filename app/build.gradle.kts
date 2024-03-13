plugins {
    alias(deps.plugins.androidApplication)
    alias(deps.plugins.kotlinAndroid)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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

    testImplementation(deps.junit)
    androidTestImplementation(deps.junitExt)
    androidTestImplementation(deps.espressoCore)
    androidTestImplementation(platform(deps.composeBom))
    androidTestImplementation(deps.junitUiTest4)

    debugImplementation(deps.composeUiTooling)
    debugImplementation(deps.composeUiTestManifest)
}