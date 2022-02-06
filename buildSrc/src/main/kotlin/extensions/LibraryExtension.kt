package extensions

import com.android.build.gradle.LibraryExtension

fun LibraryExtension.setupDefaultAndroidLibConfig(
    isViewBindingEnabled: Boolean = true
) = with(this) {
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK
        targetSdk = AndroidConfig.TARGET_SDK
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildFeatures {
        viewBinding = isViewBindingEnabled
    }

    compileOptions {
        sourceCompatibility = AndroidConfig.JAVA_VERSION
        targetCompatibility = AndroidConfig.JAVA_VERSION
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

fun LibraryExtension.setupDefaultSources() = with(this) {
    sourceSets {
        setupDefaultSources()
    }
}
