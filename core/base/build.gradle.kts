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
    implementation(Dependencies.Kodein.GENERIC)

    implementation(Dependencies.Androidx.Android.CORE_KTX)
    implementation(Dependencies.Androidx.Android.VIEWMODEL)
    implementation(Dependencies.Androidx.Android.FRAGMENT)
    implementation(Dependencies.Androidx.Android.LIFE_CYCLE)
}
