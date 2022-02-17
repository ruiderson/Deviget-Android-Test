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
import com.ruiderson.deviget_android_test.shared.domain.SharedRedditPostViewModel
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import com.ruiderson.deviget_android_test.test.di.mockKodeinModule
import io.mockk.spyk
import org.hamcrest.Matchers.not
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class MainActivityRobot(
    block: MainActivityRobot.() -> Unit
) {

    private val sharedViewModel = spyk(SharedRedditPostViewModel())

    private lateinit var activity: MainActivity

    init {
        setup()
        block.invoke(this)
    }

    private fun setup() {
        mockKodeinModule(appModule, coreModule, networkModule) {
            bind(overrides = true) from provider { sharedViewModel }
        }
    }

    inner class Arrange(block: Arrange.() -> Unit) {
        init {
            block.invoke(this)
        }

        fun setupOnShowPostDetailsState() {
            val mockedRedditPost = RedditPost(
                id = "id",
                title = "title",
                author = "author",
                num_comments = "num_comments",
                thumbnail = "thumbnail",
                created_utc = "created_utc",
                entry_date = "entry_date",
                isUnread = false
            )
            sharedViewModel.onRedditPostClicked(mockedRedditPost)
        }

        fun setupOnHidePostDetailsState() {
            sharedViewModel.onDismissAll()
        }
    }

    inner class Act(block: Act.() -> Unit) {
        init {
            block.invoke(this)
        }

        fun launchActivity() {
            launch(MainActivity::class.java).also { activityScenario ->
                activityScenario.onActivity {
                    activity = it
                    it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }
            }

        }

        fun launchActivityOnLandscape() {
            launch(MainActivity::class.java).also { activityScenario ->
                activityScenario.onActivity {
                    activity = it
                    it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                }
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
            onView(withId(R.id.postDetailsRootView))
                .check(matches(not(isDisplayed())))
        }

        fun verifyPostDetailFragmentIsDisplayed() {
            onView(withId(R.id.postDetailsRootView))
                .check(matches(isDisplayed()))
        }
    }
}
