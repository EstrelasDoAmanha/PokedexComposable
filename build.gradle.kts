// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(deps.plugins.androidApplication) apply false
    alias(deps.plugins.kotlinAndroid) apply false
    alias(deps.plugins.kotlinJvm) apply false
    alias(deps.plugins.kotlinSerialization) apply false
    alias(deps.plugins.ktlint) apply false
}

task("addPreCommitGitHookOnBuild") {
    println("⚈ ⚈ ⚈ Running Add Pre Commit Git Hook Script on Build ⚈ ⚈ ⚈")
    exec {
        commandLine("cp", "./scripts/githooks/pre-commit", "./.git/hooks")
        commandLine("chmod", "+x", "./.git/hooks/pre-commit")
    }
    println("✅ Added Pre Commit Git Hook Script.")
}