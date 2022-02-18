package com.ruiderson.deviget_android_test.base.activity

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PermissionRequestFactory {

    fun create(activity: AppCompatActivity, permission: String) = PermissionRequest(
        permission,
        PermissionResultInterceptor().apply {

            setActivityResultLauncher(
                activity.registerForActivityResult(
                    ActivityResultContracts.RequestPermission(),
                    activityResultCallback
                )
            )
        }
    )
}
