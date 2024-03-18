plugins {
    id("java-library")
    alias(deps.plugins.kotlinJvm)
    alias(deps.plugins.kotlinSerialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(deps.ktor.client.core)
    implementation(deps.ktor.client.logging)
    implementation(deps.ktor.client.android)
    implementation(deps.ktor.client.negotiation)
    implementation(deps.ktor.serialization)

    implementation(platform(deps.koin.bom))
    implementation(deps.koin.core)
}
