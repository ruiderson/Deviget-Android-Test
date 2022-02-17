package com.ruiderson.deviget_android_test.image_viewer

import android.app.Activity
import android.os.Bundle
import com.ruiderson.deviget_android_test.base.extensions.viewBinding
import com.ruiderson.deviget_android_test.databinding.ActivityImageViewerBinding

internal class ImageViewerActivity : Activity() {

    private val viewBinding: ActivityImageViewerBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
    }

    private fun setupViews() = with(viewBinding) {
    }
}
