package com.ruiderson.deviget_android_test.base.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

object ToastUtil {

    fun show(context: Context, @StringRes stringId: Int) = show(context, stringId, Toast.LENGTH_SHORT)

    fun show(context: Context, @StringRes stringId: Int, duration: Int) {
        Toast.makeText(context, context.getString(stringId), duration).show()
    }
}
