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

    @Test
    fun whenSaveImageClicked_verifyPermissionRequestIsCalled() = runImageViewerActivityTest {
        Act {
            launchActivity()
            performSaveImageClick()
        }

        Assert {
            verifyPermissionRequestIsCalled()
        }
    }

    @Test
    fun whenSaveImageClicked_verifyImageToolIsCalled() = runImageViewerActivityTest {
        Act {
            launchActivity()
            performSaveImageClick()
        }

        Assert {
            verifyImageToolIsCalled()
        }
    }

    @Test
    fun whenSaveImageClicked_andIsSuccess_verifySuccessToastIsDisplayed() = runImageViewerActivityTest {
        Act {
            launchActivity()
            performSaveImageClick()
        }

        Assert {
            verifySuccessToastIsDisplayed()
        }
    }

    @Test
    fun whenSaveImageClicked_andIsFailed_verifyFailedToastIsDisplayed() = runImageViewerActivityTest {
        Arrange {
            setupFailedSaveBitmap()
        }

        Act {
            launchActivity()
            performSaveImageClick()
        }

        Assert {
            verifyFailedToastIsDisplayed()
        }
    }

    private fun runImageViewerActivityTest(
        block: ImageViewerActivityRobot.() -> Unit
    ) = runBlockingTest {
        ImageViewerActivityRobot(block)
    }
}
