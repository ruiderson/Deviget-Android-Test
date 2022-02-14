import extensions.setupDefaultAndroidConfig
import extensions.setupDefaultSources

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("plugin.serialization")
    kotlin("kapt")
}

android {
    setupDefaultAndroidConfig()
    setupDefaultSources()

    kotlinOptions {
        jvmTarget = AndroidConfig.JVM_TARGET
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
}

dependencies {
    kapt(Dependencies.Androidx.Android.ROOM_COMPILER)

    implementation(Dependencies.Androidx.Android.APPCOMPAT)
    implementation(Dependencies.Androidx.Android.ROOM_KTX)
    implementation(Dependencies.Androidx.Android.ROOM_RUNTIME)
    implementation(Dependencies.Androidx.Android.ROOM_PAGING)
    implementation(Dependencies.Androidx.Android.SLIDING_PANEL_LAYOUT)
    implementation(Dependencies.Androidx.Android.SWIPE_REFRESH_LAYOUT)
    implementation(Dependencies.Androidx.Android.VIEWMODEL)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.Kodein.GENERIC)
    implementation(Dependencies.Kotlin.Android.SERIALIZATION)

    testImplementation(Dependencies.Junit.JUNIT)
    testImplementation(Dependencies.Kotlin.Test.COROUTINES)
    testImplementation(Dependencies.Mockk.Test.CORE)

    androidTestImplementation(Dependencies.Androidx.AndroidTest.CORE)
    androidTestImplementation(Dependencies.Androidx.AndroidTest.ESPRESSO_CORE)
    androidTestImplementation(Dependencies.Androidx.AndroidTest.JUNIT)

    debugImplementation(Dependencies.Androidx.AndroidTest.FRAGMENT)

    implementation(project(Modules.Core.BASE))
    implementation(project(Modules.Core.NETWORK))
    implementation(project(Modules.Core.STYLEGUIDE))
    implementation(project(Modules.Core.TEST))
}
