import extensions.setupDefaultAndroidConfig
import extensions.setupDefaultSources

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
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
    kapt(Dependencies.Glide.GLIDE_COMPILER)

    implementation(Dependencies.Androidx.Android.ACTIVITY)
    implementation(Dependencies.Androidx.Android.APPCOMPAT)
    implementation(Dependencies.Androidx.Android.FRAGMENT)
    implementation(Dependencies.Androidx.Android.PAGING)
    implementation(Dependencies.Androidx.Android.ROOM_KTX)
    implementation(Dependencies.Androidx.Android.ROOM_RUNTIME)
    implementation(Dependencies.Androidx.Android.ROOM_PAGING)
    implementation(Dependencies.Androidx.Android.SLIDING_PANEL_LAYOUT)
    implementation(Dependencies.Androidx.Android.SWIPE_REFRESH_LAYOUT)
    implementation(Dependencies.Androidx.Android.VIEWMODEL)
    implementation(Dependencies.Glide.GLIDE)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.Kodein.ANDROID_FRAMEWORK)
    implementation(Dependencies.Kodein.GENERIC)
    implementation(Dependencies.Kotlin.Android.SERIALIZATION)

    testImplementation(Dependencies.Junit.JUNIT)
    testImplementation(Dependencies.Kotlin.Test.COROUTINES)
    testImplementation(Dependencies.Mockk.Test.CORE)

    androidTestImplementation(Dependencies.Androidx.AndroidTest.CORE)
    implementation(Dependencies.Androidx.AndroidTest.ESPRESSO_CONTRIB)
    androidTestImplementation(Dependencies.Androidx.AndroidTest.ESPRESSO_CORE)
    androidTestImplementation(Dependencies.Androidx.AndroidTest.JUNIT)
    androidTestImplementation(Dependencies.Kotlin.Test.COROUTINES)
    androidTestImplementation(Dependencies.Mockk.AndroidTest.ANDROID)

    implementation(project(Modules.Core.BASE))
    implementation(project(Modules.Core.NETWORK))
    implementation(project(Modules.Core.STYLEGUIDE))
    testImplementation(project(Modules.Core.TEST))
    androidTestImplementation(project(Modules.Core.TEST))

    debugImplementation(Dependencies.Androidx.AndroidTest.FRAGMENT)
}
