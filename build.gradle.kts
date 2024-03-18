// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(deps.plugins.androidApplication) apply false
    alias(deps.plugins.kotlinAndroid) apply false
    alias(deps.plugins.kotlinJvm) apply false
    alias(deps.plugins.kotlinSerialization) apply false
}