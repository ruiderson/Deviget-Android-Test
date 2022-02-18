package com.ruiderson.deviget_android_test.image_viewer.domain

import android.Manifest
import com.ruiderson.deviget_android_test.base.activity.PermissionRequestFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class ImageViewerViewModelTest {

    private val permissionRequestFactory: PermissionRequestFactory = mockk {
        every { create(any(), any()) } returns mockk()
    }

    private val kodein = Kodein {
        bind() from provider { permissionRequestFactory }
    }

    private val viewModel = ImageViewerViewModel(kodein)

    @Test
    fun whenCreateAccessMediaPermissionRequest_verifyPermissionRequestFactoryIsCalled() {
        viewModel.createAccessMediaPermissionRequest(mockk())

        verify { permissionRequestFactory.create(any(), ACCESS_MEDIA_PERMISSION) }
    }

    companion object {
        const val ACCESS_MEDIA_PERMISSION = Manifest.permission.ACCESS_MEDIA_LOCATION
    }
}
