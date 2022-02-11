object Dependencies {

    object Plugins {
        const val ANDROID = "com.android.tools.build:gradle:${Versions.Plugins.ANDROID}"
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugins.KOTLIN}"
        const val KOTLIN_SERIALIZATION = "org.jetbrains.kotlin:kotlin-serialization:${Versions.Plugins.KOTLIN}"
    }

    object Junit {
        const val JUNIT = "junit:junit:${Versions.Junit.JUNIT}"
    }

    object Mockk {
        object Test {
            const val CORE = "io.mockk:mockk:${Versions.Mockk.MOCKK}"
        }

        object AndroidTest {
            const val ANDROID = "io.mockk:mockk-android:${Versions.Mockk.MOCKK}"
        }
    }

    object Kotlin {
        object Android {
            const val SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.SERIALIZATION}"
            const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.COROUTINES}"
            const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.COROUTINES}"
        }

        object Test {
            const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.COROUTINES}"
        }
    }

    object Androidx {
        object Android {
            const val CORE_KTX = "androidx.core:core-ktx:${Versions.Androidx.Android.CORE}"
            const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.Androidx.Android.APPCOMPAT}"
            const val SLIDING_PANEL_LAYOUT = "androidx.slidingpanelayout:slidingpanelayout:${Versions.Androidx.Android.SLIDING_PANEL_LAYOUT}"
            const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.Androidx.Android.SWIPE_REFRESH_LAYOUT}"
            const val PAGING = "androidx.paging:paging-runtime-ktx:${Versions.Androidx.Android.PAGING}"
            const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Androidx.Android.LIFE_CYCLE}"
            const val LIFE_CYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Androidx.Android.LIFE_CYCLE}"
            const val FRAGMENT = "androidx.fragment:fragment-ktx:${Versions.Androidx.Android.FRAGMENT}"
            const val ROOM_KTX = "androidx.room:room-ktx:${Versions.Androidx.Android.ROOM}"
            const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.Androidx.Android.ROOM}"
            const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.Androidx.Android.ROOM}"
            const val ROOM_PAGING = "androidx.room:room-paging:${Versions.Androidx.Android.ROOM}"
        }

        object AndroidTest {
            const val CORE = "androidx.test:core-ktx:${Versions.Androidx.AndroidTest.CORE}"
            const val JUNIT = "androidx.test.ext:junit:${Versions.Androidx.AndroidTest.JUNIT}"
            const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.Androidx.AndroidTest.ESPRESSO}"
        }
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:${Versions.Google.MATERIAL}"
    }

    object Ktor {
        const val ANDROID_CLIENT = "io.ktor:ktor-client-android:${Versions.Ktor.KTOR_CLIENT}"
        const val SERIALIZATION = "io.ktor:ktor-client-serialization:${Versions.Ktor.KTOR_CLIENT}"
        const val LOGGING = "io.ktor:ktor-client-logging-jvm:${Versions.Ktor.KTOR_CLIENT}"
    }

    object Kodein {
        const val GENERIC = "org.kodein.di:kodein-di-generic-jvm:${Versions.Kodein.KODEIN}"
        const val ANDROID_FRAMEWORK = "org.kodein.di:kodein-di-framework-android-x:${Versions.Kodein.KODEIN}"
    }
}
