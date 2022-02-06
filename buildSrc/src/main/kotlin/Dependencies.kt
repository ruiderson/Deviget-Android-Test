object Dependencies {

    object Plugins {
        const val ANDROID = "com.android.tools.build:gradle:${Versions.Plugins.ANDROID}"
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugins.KOTLIN}"
    }

    object Junit {
        const val JUNIT = "junit:junit:${Versions.Junit.JUNIT}"
    }

    object Androidx {
        object Android {
            const val CORE_KTX = "androidx.core:core-ktx:${Versions.Androidx.Android.CORE}"
            const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.Androidx.Android.APPCOMPAT}"
            const val SLIDING_PANEL_LAYOUT = "androidx.slidingpanelayout:slidingpanelayout:${Versions.Androidx.Android.SLIDING_PANEL_LAYOUT}"
        }

        object AndroidTest {
            const val CORE = "androidx.test:core-ktx:${Versions.Androidx.AndroidTest.CORE}"
            const val JUNIT = "androidx.test.ext:junit:${Versions.Androidx.AndroidTest.JUNIT}"
            const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.Androidx.AndroidTest.ESPRESSO}"
        }
    }

    object Google {
        object Android {
            const val MATERIAL = "com.google.android.material:material:${Versions.Google.Android.MATERIAL}"
        }
    }
}
