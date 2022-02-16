import org.gradle.api.JavaVersion

object AndroidConfig {

    const val APPLICATION_ID = "com.ruiderson.android_test"
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    const val COMPILE_SDK = 31
    const val TARGET_SDK = COMPILE_SDK
    const val MIN_SDK = 26

    const val TEST_INSTRUMENTATION_RUNNER = "com.ruiderson.deviget_android_test.test.runner.KodeinTestRunner"
    const val TEST_INSTRUMENTATION_RUNNER_DEFAULT = "androidx.test.runner.AndroidJUnitRunner"

    val JAVA_VERSION = JavaVersion.VERSION_1_8
    const val JVM_TARGET = "1.8"
}
