plugins {
    alias(deps.plugins.kotlinJvm)
    alias(deps.plugins.kotlinSerialization)
    alias(deps.plugins.ktlint)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(platform(deps.koin.bom))
    implementation(deps.koin.core)
    implementation(deps.kotlinx.coroutines.android)
}
