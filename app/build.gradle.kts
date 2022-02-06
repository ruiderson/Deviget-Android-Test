import extensions.setupDefaultAndroidConfig
import extensions.setupDefaultSources

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    setupDefaultAndroidConfig()
    setupDefaultSources()

    kotlinOptions {
        jvmTarget = AndroidConfig.JVM_TARGET
    }
}

dependencies {
    implementation(Dependencies.Androidx.Android.CORE_KTX)
    implementation(Dependencies.Androidx.Android.APPCOMPAT)
    implementation(Dependencies.Google.Android.MATERIAL)
    implementation(Dependencies.Androidx.Android.SLIDING_PANEL_LAYOUT)

    testImplementation(Dependencies.Junit.JUNIT)

    androidTestImplementation(Dependencies.Androidx.AndroidTest.CORE)
    androidTestImplementation(Dependencies.Androidx.AndroidTest.JUNIT)
    androidTestImplementation(Dependencies.Androidx.AndroidTest.ESPRESSO_CORE)
}
