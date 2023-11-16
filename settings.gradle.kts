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
include(":feature:web")
include(":feature:search")
include(":feature:hospital-favorite")
include(":feature:hospital-detail")
include(":domain:hospital-list")
include(":domain:folder-list")
include(":data:hospitals")
include(":data:folders")
include(":base:navigation")
include(":core:database")
include(":domain:favorite-hospital")
