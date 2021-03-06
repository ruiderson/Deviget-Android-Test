package com.ruiderson.deviget_android_test.image_viewer.domain

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.ruiderson.deviget_android_test.base.activity.PermissionRequestFactory
import com.ruiderson.deviget_android_test.base.utils.ImageTool
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

internal class ImageViewerViewModel(
    override val kodein: Kodein
) : ViewModel(), KodeinAware {

    private val permissionRequestFactory: PermissionRequestFactory by kodein.instance()

    private val imageTool: ImageTool by kodein.instance()

    fun createAccessMediaPermissionRequest(
        activity: AppCompatActivity
    ) = permissionRequestFactory.create(activity, ACCESS_MEDIA_PERMISSION)

    fun saveBitmap(bitmap: Bitmap) = imageTool.saveBitmap(bitmap)

    companion object {
        @SuppressLint("InlinedApi")
        const val ACCESS_MEDIA_PERMISSION = Manifest.permission.ACCESS_MEDIA_LOCATION
    }
}
