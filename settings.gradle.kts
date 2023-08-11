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

rootProject.name = "MutipleModuleApp"

include(":app")
include(":core")
include(":SuperApp")
include(":core:data")
include(":core:domain")
include(":core:common")
include(":core:network")
