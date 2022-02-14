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
    implementation(Dependencies.Androidx.Android.APPCOMPAT)
    implementation(Dependencies.Androidx.Android.SLIDING_PANEL_LAYOUT)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.Kodein.GENERIC)

    implementation(project(Modules.Core.BASE))
    implementation(project(Modules.Core.NETWORK))
    implementation(project(Modules.Core.TEST))
}
