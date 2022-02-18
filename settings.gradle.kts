pluginManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Deviget-Android-Test"
include(
    ":app",
    ":core:base",
    ":core:network",
    ":core:styleguide",
    ":core:test"
)
