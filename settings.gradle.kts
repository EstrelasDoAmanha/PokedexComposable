pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    versionCatalogs {
        create("deps") {
            from(files("./gradle/libs.versions.toml"))
        }
    }

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PokedexCompose"
include(
    ":app",
    ":network",
    ":designsystem",
    ":flipper",
    ":features:home:public",
    ":features:home:implementation",
    ":features:details:public",
    ":features:details:implementation"
)
