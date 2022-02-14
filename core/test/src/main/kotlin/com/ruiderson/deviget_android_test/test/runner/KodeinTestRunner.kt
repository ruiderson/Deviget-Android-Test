package com.ruiderson.deviget_android_test.test.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.ruiderson.deviget_android_test.test.di.AndroidTestApplication

open class KodeinTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, AndroidTestApplication::class.java.name, context)
    }
}
