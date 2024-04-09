plugins {
    alias(deps.plugins.androidLibrary)
    alias(deps.plugins.kotlinAndroid)
    alias(deps.plugins.ktlint)
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
    buildFeatures {
        compose = deps.versions.composeProject.get().toBoolean()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = deps.versions.kotlinCompilerExtVersion.get()
    }
}

dependencies {

    implementation(deps.coreKtx)
    implementation(deps.appCompat)
    implementation(deps.material)

    implementation(platform(deps.compose.bom))
    implementation(deps.compose.ui)
    implementation(deps.compose.text)
    implementation(deps.compose.ui.graphics)
    implementation(deps.compose.ui.tooling.preview)
    implementation(deps.compose.material3)

    implementation(deps.navigation.runtime.ktx)

    implementation(deps.lottie)

    testImplementation(deps.junit)
    androidTestImplementation(deps.junitExt)
    androidTestImplementation(deps.espressoCore)
}
