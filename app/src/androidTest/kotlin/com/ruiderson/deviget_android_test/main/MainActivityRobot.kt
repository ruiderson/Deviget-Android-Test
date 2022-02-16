package com.ruiderson.deviget_android_test.main

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.ruiderson.deviget_android_test.R
import com.ruiderson.deviget_android_test.base.di.coreModule
import com.ruiderson.deviget_android_test.di.appModule
import com.ruiderson.deviget_android_test.network.di.networkModule
import com.ruiderson.deviget_android_test.test.di.mockKodeinModule
import org.hamcrest.Matchers.not

class MainActivityRobot(block: MainActivityRobot.() -> Unit) {

    private lateinit var activityScenario: ActivityScenario<MainActivity>

    init {
        setup()
        block.invoke(this)
    }

    private fun setup() {
        mockKodeinModule(appModule, coreModule, networkModule) {
        }
    }

    inner class Act(block: Act.() -> Unit) {
        init {
            block.invoke(this)
        }

        fun launchActivity() {
            activityScenario = launch(MainActivity::class.java)
            activityScenario.onActivity {
                it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }

        fun launchActivityOnLandscape() {
            activityScenario = launch(MainActivity::class.java)
            activityScenario.onActivity {
                it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        }
    }

    inner class Assert(block: Assert.() -> Unit) {
        init {
            block.invoke(this)
        }

        fun verifyTopPostsFragmentIsDisplayed() {
            onView(withId(R.id.topPostsRootView))
                .check(matches(isDisplayed()))
        }

        fun verifyPostDetailFragmentIsNotDisplayed() {
            onView(withId(R.id.postDetailsFragmentView))
                .check(matches(not(isDisplayed())))
        }

        fun verifyPostDetailFragmentIsDisplayed() {
            onView(withId(R.id.postDetailsFragmentView))
                .check(matches(isDisplayed()))
        }
    }
}
