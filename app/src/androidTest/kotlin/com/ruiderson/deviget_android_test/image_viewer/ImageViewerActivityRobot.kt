package com.ruiderson.deviget_android_test.image_viewer

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import com.ruiderson.deviget_android_test.image_viewer.domain.ImageViewerNavigation
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import org.junit.Assert.assertTrue
import com.ruiderson.deviget_android_test.R
import com.ruiderson.deviget_android_test.base.activity.PermissionRequest
import com.ruiderson.deviget_android_test.base.activity.PermissionRequestFactory
import com.ruiderson.deviget_android_test.base.di.coreModule
import com.ruiderson.deviget_android_test.di.appModule
import com.ruiderson.deviget_android_test.image_viewer.domain.ImageViewerViewModel
import com.ruiderson.deviget_android_test.test.di.getKodeinInstance
import com.ruiderson.deviget_android_test.test.di.mockKodeinModule
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class ImageViewerActivityRobot(
    block: ImageViewerActivityRobot.() -> Unit
) {

    private val kodein = getKodeinInstance()

    private val permissionRequest = spyk(PermissionRequest("", mockk())) {
        every { request(any(), any(), any()) } answers { }
    }

    private val permissionRequestFactory = spyk(PermissionRequestFactory()) {
        every { create(any(), any()) } returns permissionRequest
    }

    private val mockedRedditPost = RedditPost(
        id = "id",
        title = "title",
        author = "author",
        num_comments = "num_comments",
        thumbnail = "https://play-lh.googleusercontent.com/8Vw-7MAm558750a4M55fiOlUf7lP2cYnFuqSWynrygIiyEEiQQDa_xxHKYOX83L0UD2T=s180",
        created_utc = "created_utc",
        entry_date = "entry_date",
        isUnread = false
    )

    private val viewModel = spyk(ImageViewerViewModel(kodein))

    private lateinit var activity: ImageViewerActivity

    init {
        setup()
        block.invoke(this)
    }

    private fun setup() {
        mockKodeinModule(appModule, coreModule) {
            bind(overrides = true) from provider { permissionRequestFactory }
            bind(overrides = true) from provider { viewModel }
        }
    }

    inner class Act(block: Act.() -> Unit) {
        init {
            block.invoke(this)
        }

        fun launchActivity() {
            val intent = Intent(
                ApplicationProvider.getApplicationContext(),
                ImageViewerActivity::class.java
            ).apply {
                putExtra(ImageViewerNavigation.REDDIT_POST_EXTRA, mockedRedditPost)
            }
            ActivityScenario.launch<ImageViewerActivity>(intent).also { activityScenario ->
                activityScenario.onActivity {
                    activity = it
                }
            }
        }

        fun performNavigationButtonClick() {
            onView(withContentDescription(R.string.navigation_back_description))
                .perform(click())
        }

        fun performSaveImageClick() {
            onView(withContentDescription(R.string.save_image))
                .perform(click())
        }
    }

    inner class Assert(block: Assert.() -> Unit) {
        init {
            block.invoke(this)
        }

        fun verifyActivityIsFinished() {
            assertTrue(activity.isFinishing)
        }

        fun verifyToolbarTitleIsCorrect() {
            onView(withId(R.id.toolbar))
                .check(matches(hasDescendant(withText(mockedRedditPost.title))))
        }

        fun verifyPermissionRequestIsCalled() {
            verify { permissionRequest.request(any(), any(), any()) }
        }
    }
}
