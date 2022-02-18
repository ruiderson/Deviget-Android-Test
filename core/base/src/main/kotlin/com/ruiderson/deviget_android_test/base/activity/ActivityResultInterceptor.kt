package com.ruiderson.deviget_android_test.base.activity

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher

class PermissionResultInterceptor: ActivityResultInterceptor<String, Boolean>()

abstract class ActivityResultInterceptor<T1, T2> {

    private var activityResultLauncher: ActivityResultLauncher<T1>? = null

    private var resultCallbackInterceptor: ((T2) -> Unit)? = null

    val activityResultCallback = ActivityResultCallback<T2> {
        resultCallbackInterceptor?.invoke(it)
    }

    fun setActivityResultLauncher(resultCallbackInterceptor: ActivityResultLauncher<T1>) {
        this.activityResultLauncher = resultCallbackInterceptor
    }

    fun launch(input: T1, resultCallbackInterceptor: (T2) -> Unit) {
        this.resultCallbackInterceptor = resultCallbackInterceptor
        activityResultLauncher?.launch(input)
    }
}
