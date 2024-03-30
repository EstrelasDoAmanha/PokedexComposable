plugins {
    alias(deps.plugins.androidLibrary)
    alias(deps.plugins.kotlinAndroid)
}

android {
    namespace = deps.versions.namespace.get() + ".designsystem"
    compileSdk = deps.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = deps.versions.minSdk.get().toInt()

        testInstrumentationRunner = deps.versions.testInstrumentationRunner.get()
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(deps.coreKtx)
    implementation(deps.appCompat)
    implementation(deps.material)

    implementation(platform(deps.composeBom))
    implementation(deps.composeUi)
    implementation(deps.composeText)
    implementation(deps.composeUiGraphics)
    implementation(deps.composeUiToolingPreview)
    implementation(deps.composeMaterial3)

    testImplementation(deps.junit)
    androidTestImplementation(deps.junitExt)
    androidTestImplementation(deps.espressoCore)
}