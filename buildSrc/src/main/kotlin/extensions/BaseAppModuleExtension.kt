package extensions

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

/*
Setup the default Android app configurations
*/
fun BaseAppModuleExtension.setupDefaultAndroidConfig(
    isViewBindingEnabled: Boolean = true
) = with(this) {
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        applicationId = AndroidConfig.APPLICATION_ID
        minSdk = AndroidConfig.MIN_SDK
        targetSdk = AndroidConfig.TARGET_SDK
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME

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

    testOptions {
        animationsDisabled = true
    }

    packagingOptions {
        resources.excludes += "META-INF/AL2.0"
        resources.excludes += "META-INF/LGPL2.1"
        resources.excludes += "META-INF/licenses/ASM"
    }
}

/*
Setup the default Android app sources (main, test and androidTest)
*/
fun BaseAppModuleExtension.setupDefaultSources() = with(this) {
    sourceSets {
        setupDefaultSources()
    }
}