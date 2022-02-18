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
    implementation(Dependencies.Androidx.Android.APPCOMPAT)
    implementation(Dependencies.Androidx.Android.FRAGMENT)
    implementation(Dependencies.Androidx.Android.LIFE_CYCLE)
    implementation(Dependencies.Androidx.Android.VIEWMODEL)
    implementation(Dependencies.Kodein.GENERIC)
}
