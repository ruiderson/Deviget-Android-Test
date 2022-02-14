package com.ruiderson.deviget_android_test.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun whenLaunchActivity_verifyTopPostsFragmentIsDisplayed() {
        MainActivityRobot {
            launchActivity()

            verifyTopPostsFragmentIsDisplayed()
        }
    }

    @Test
    fun whenLaunchActivityOnLandscape_verifyTopPostsFragmentIsDisplayed() {
        MainActivityRobot {
            launchActivityOnLandscape()

            verifyTopPostsFragmentIsDisplayed()
        }
    }

    @Test
    fun whenLaunchActivity_verifyPostDetailFragmentIsNotDisplayed() {
        MainActivityRobot {
            launchActivity()

            verifyPostDetailFragmentIsNotDisplayed()
        }
    }

    @Test
    fun whenLaunchActivityOnLandscape_verifyPostDetailFragmentIsDisplayed() {
        MainActivityRobot {
            launchActivityOnLandscape()

            verifyPostDetailFragmentIsDisplayed()
        }
    }
}
