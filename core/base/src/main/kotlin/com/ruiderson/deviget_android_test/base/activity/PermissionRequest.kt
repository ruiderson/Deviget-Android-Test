package com.ruiderson.deviget_android_test.base.activity

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.ruiderson.deviget_android_test.base.R

/*
Creates an Android permission request. Always create new instances before or on Activity onCreate.
Multiple instances for different permissions allowed.
Permission example: Manifest.permission.ACCESS_MEDIA_LOCATION
*/
class PermissionRequest(
    private val permission: String,
    private val interceptor: PermissionResultInterceptor
) {

    private var isRequestPermissionRationale = false

    private var requestCode: Int = DEFAULT_REQUEST_CODE

    private var requestDenialMessage: Int? = DEFAULT_REQUEST_DENIAL_MESSAGE_ID

    fun setRequestCode(requestCode: Int) {
        this.requestCode = requestCode
    }

    fun setOnRequestDenialMessage(@StringRes stringId: Int?) {
        requestDenialMessage = stringId
    }

    fun request(
        activity: AppCompatActivity,
        onPermissionDenied: (() -> Unit)? = null,
        onPermissionGranted: () -> Unit
    ) = interceptor.launch(permission) { isGranted ->

        when {

            isGranted -> onPermissionGranted()

            isRequestPermissionRationale -> {
                onPermissionDenied?.invoke()
                showRequestPermissionRationale(activity)
            }

            else -> {
                isRequestPermissionRationale = true
                runRequestPermission(activity)
            }
        }
    }

    private fun runRequestPermission(
        activity: AppCompatActivity
    ) = activity.requestPermissions(
        arrayOf(permission),
        requestCode
    )

    private fun showRequestPermissionRationale(context: Context) {
        requestDenialMessage?.let {
            Toast.makeText(context, context.getString(it), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val DEFAULT_REQUEST_CODE = 999
        private val DEFAULT_REQUEST_DENIAL_MESSAGE_ID = R.string.default_permission_request_denial
    }
}
