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
}

rootProject.name = "HospitalSearch"
include(":app")
include(":data:hospitals")
include(":feature:search")
include(":domain:hospital-list")
include(":feature:hospital-detail")
include(":core:database")
