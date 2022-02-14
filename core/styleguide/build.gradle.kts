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
    implementation(Dependencies.Google.MATERIAL)
}
