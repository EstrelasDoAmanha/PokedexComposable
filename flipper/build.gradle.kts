plugins {
    alias(deps.plugins.androidLibrary)
    alias(deps.plugins.kotlinAndroid)
    alias(deps.plugins.ktlint)
}

android {
    namespace = deps.versions.namespace.get() + ".flipper"
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
        debug {
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

    debugImplementation(deps.bundles.flipper.bundle)
    releaseImplementation(deps.flipper.noop)
    releaseImplementation(deps.flipper.noop.glenn)

    testImplementation(deps.junit)
    androidTestImplementation(deps.junitExt)
    androidTestImplementation(deps.espressoCore)
}
