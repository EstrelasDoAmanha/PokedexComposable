plugins {
    alias(deps.plugins.androidLibrary)
    alias(deps.plugins.kotlinAndroid)
    alias(deps.plugins.ktlint)
}

android {
    namespace = deps.versions.namespace.get() + ".listImpl"
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

    implementation(platform(deps.compose.bom))
    implementation(deps.compose.ui)
    implementation(deps.compose.ui.graphics)
    implementation(deps.compose.ui.tooling.preview)
    implementation(deps.compose.material3)

    testImplementation(deps.junit)
    androidTestImplementation(deps.junitExt)
    androidTestImplementation(deps.espressoCore)
}
