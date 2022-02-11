import extensions.setupDefaultAndroidConfig
import extensions.setupDefaultSources

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("plugin.serialization")
}

android {
    setupDefaultAndroidConfig()
    setupDefaultSources()

    kotlinOptions {
        jvmTarget = AndroidConfig.JVM_TARGET
    }
}

dependencies {
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.Androidx.Android.CORE_KTX)
    implementation(Dependencies.Androidx.Android.APPCOMPAT)
    implementation(Dependencies.Androidx.Android.VIEWMODEL)
    implementation(Dependencies.Androidx.Android.LIFE_CYCLE)
    implementation(Dependencies.Androidx.Android.SLIDING_PANEL_LAYOUT)
    implementation(Dependencies.Kodein.GENERIC)
    implementation(Dependencies.Kodein.ANDROID_FRAMEWORK)
    implementation(Dependencies.Kotlin.Android.SERIALIZATION)
    implementation(Dependencies.Kotlin.Android.COROUTINES_CORE)
    implementation(Dependencies.Kotlin.Android.COROUTINES_ANDROID)

    implementation(project(Modules.Core.BASE))
    implementation(project(Modules.Core.NETWORK))

    testImplementation(Dependencies.Junit.JUNIT)
    testImplementation(Dependencies.Kotlin.Test.COROUTINES)

    androidTestImplementation(Dependencies.Androidx.AndroidTest.CORE)
    androidTestImplementation(Dependencies.Androidx.AndroidTest.JUNIT)
    androidTestImplementation(Dependencies.Androidx.AndroidTest.ESPRESSO_CORE)
}
