package com.ruiderson.deviget_android_test.image_viewer

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ImageViewerActivityTest {

    @Test
    fun whenNavigationButtonClicked_verifyActivityIsFinished() = runImageViewerActivityTest {
        Act {
            launchActivity()
            performNavigationButtonClick()
        }

        Assert {
            verifyActivityIsFinished()
        }
    }

    @Test
    fun whenLaunchActivity_verifyToolbarTitleIsCorrect() = runImageViewerActivityTest {
        Act {
            launchActivity()
        }

        Assert {
            verifyToolbarTitleIsCorrect()
        }
    }

    private fun runImageViewerActivityTest(
        block: ImageViewerActivityRobot.() -> Unit
    ) = runBlockingTest {
        ImageViewerActivityRobot(block)
    }
}
