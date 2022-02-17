package com.ruiderson.deviget_android_test.base.utils

import android.content.Context
import android.content.Intent

class IntentFactory(
    private val context: Context
) {

    fun create(cls: Class<*>?) = Intent(context, cls)
}
