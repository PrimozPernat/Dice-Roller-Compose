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

rootProject.name = "Dice Roller Compose"
include(":app")
include(":app:lemonade")
include(":app:tipcalcultor")
include(":app:artspaceapp")
include(":app:listapp")
include(":app:dogapp")
include(":app:superhero")
include(":app:30daysapp")
