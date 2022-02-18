import extensions.setupDefaultAndroidLibConfig
import extensions.setupDefaultSources

plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    setupDefaultAndroidLibConfig(isViewBindingEnabled = false)
    setupDefaultSources()

    kotlinOptions {
        jvmTarget = AndroidConfig.JVM_TARGET
    }
}

dependencies {
    implementation(Dependencies.Kodein.GENERIC)
    implementation(Dependencies.Ktor.ANDROID_CLIENT)
    implementation(Dependencies.Ktor.LOGGING)
    implementation(Dependencies.Ktor.SERIALIZATION)
}
