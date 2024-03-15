plugins {
    id("java-library")
    alias(deps.plugins.kotlinJvm)
    alias(deps.plugins.kotlinSerialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(deps.ktorClientCore)
    implementation(deps.ktorClientLogging)
    implementation(deps.ktorClientAndroid)
    implementation(deps.ktorClientNegotiation)
    implementation(deps.ktorSerialization)

    implementation(platform(deps.koin.bom))
    implementation(deps.koin.core)
}
