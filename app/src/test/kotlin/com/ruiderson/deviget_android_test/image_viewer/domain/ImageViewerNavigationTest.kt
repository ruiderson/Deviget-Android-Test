package com.ruiderson.deviget_android_test.image_viewer.domain

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class ImageViewerNavigationTest {

    private val context: Context = mockk {
        every { startActivity(any()) } answers { }
    }

    private val navigation = ImageViewerNavigation()

    @Test
    fun whenNavigateToImageViewer_verifyStartActivityIsCalled() {
        navigation.navigateToImageViewer(context, mockk())

        verify { context.startActivity(any()) }
    }
}
