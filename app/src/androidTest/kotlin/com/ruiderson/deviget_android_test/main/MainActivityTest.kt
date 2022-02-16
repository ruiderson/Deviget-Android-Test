package com.ruiderson.deviget_android_test.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun whenLaunchActivity_verifyTopPostsFragmentIsDisplayed() = runMainActivityTest {
        Act {
            launchActivity()
        }

        Assert {
            verifyTopPostsFragmentIsDisplayed()
        }
    }

    @Test
    fun whenLaunchActivityOnLandscape_verifyTopPostsFragmentIsDisplayed() = runMainActivityTest {
        Act {
            launchActivityOnLandscape()
        }

        Assert {
            verifyTopPostsFragmentIsDisplayed()
        }
    }

    @Test
    fun whenLaunchActivity_verifyPostDetailFragmentIsNotDisplayed() = runMainActivityTest {
        Act {
            launchActivity()
        }

        Assert {
            verifyPostDetailFragmentIsNotDisplayed()
        }
    }

    @Test
    fun whenLaunchActivityOnLandscape_verifyPostDetailFragmentIsDisplayed() = runMainActivityTest {
        Act {
            launchActivityOnLandscape()
        }

        Assert {
            verifyPostDetailFragmentIsDisplayed()
        }
    }

    fun runMainActivityTest(
        block: MainActivityRobot.() -> Unit
    ) {
        MainActivityRobot(block)
    }
}
