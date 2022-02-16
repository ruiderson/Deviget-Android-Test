import extensions.setupDefaultAndroidLibConfig
import extensions.setupDefaultSources

plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    setupDefaultAndroidLibConfig()
    setupDefaultSources()

    kotlinOptions {
        jvmTarget = AndroidConfig.JVM_TARGET
    }
}

dependencies {
    implementation(Dependencies.Androidx.Android.VIEWMODEL)
    implementation(Dependencies.Androidx.AndroidTest.CORE)
    implementation(Dependencies.Androidx.AndroidTest.TEST_RUNNER)
    implementation(Dependencies.Junit.JUNIT)
    implementation(Dependencies.Kodein.CONF_JVM)
    implementation(Dependencies.Kodein.GENERIC)
    implementation(Dependencies.Kotlin.Test.COROUTINES) {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
    }
}
