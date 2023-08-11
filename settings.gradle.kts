pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("gradle") {
            from(files("gradle/gradle.versions.toml"))
        }
    }
}

rootProject.name = "HospitalSearch"
include(":app")
include(":data:hospitals")
include(":feature:search")
include(":domain:hospital-list")
