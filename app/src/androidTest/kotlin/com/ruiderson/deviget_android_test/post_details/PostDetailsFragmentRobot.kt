package com.ruiderson.deviget_android_test.post_details

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.ruiderson.deviget_android_test.R
import com.ruiderson.deviget_android_test.di.appModule
import com.ruiderson.deviget_android_test.shared.domain.SharedRedditPostViewModel
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import com.ruiderson.deviget_android_test.test.di.mockKodeinModule
import io.mockk.spyk
import org.hamcrest.Matchers.not
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class PostDetailsFragmentRobot(
    block: PostDetailsFragmentRobot.() -> Unit
) {

    private val sharedViewModel = spyk(SharedRedditPostViewModel())

    private lateinit var fragmentScenario: FragmentScenario<PostDetailsFragment>

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

    init {
        setup()
        block.invoke(this)
    }

    private fun setup() {
        mockKodeinModule(appModule) {
            bind(overrides = true) from provider { sharedViewModel }
        }
    }

    inner class Arrange(block: Arrange.() -> Unit) {
        init {
            block.invoke(this)
        }

        fun setupOnHidePostDetailsState() {
            sharedViewModel.onRedditPostClicked(mockedRedditPost)
            sharedViewModel.onRedditPostDismissed(mockedRedditPost)
        }

        fun setupOnShowPostDetailsState() {
            sharedViewModel.onRedditPostClicked(mockedRedditPost)
        }

        fun setupInvalidThumbnailPost() {
            val redditPost = RedditPost(
                id = mockedRedditPost.id,
                title = mockedRedditPost.title,
                author = mockedRedditPost.author,
                num_comments = mockedRedditPost.num_comments,
                thumbnail = "self",
                created_utc = mockedRedditPost.created_utc,
                entry_date = mockedRedditPost.entry_date,
                isUnread = mockedRedditPost.isUnread
            )
            sharedViewModel.onRedditPostClicked(redditPost)
        }
    }

    inner class Act(block: Act.() -> Unit) {
        init {
            block.invoke(this)
        }

        fun launchFragment() {
            fragmentScenario = launchFragmentInContainer(
                themeResId = R.style.Theme_AppTheme
            )
        }
    }

    inner class Assert(block: Assert.() -> Unit) {
        init {
            block.invoke(this)
        }

        fun verifyAuthorIsNotVisible() {
            onView(withId(R.id.postDetailsAuthor))
                .check(matches(not(isDisplayed())))
        }

        fun verifyImageIsNotVisible() {
            onView(withId(R.id.postDetailsImageView))
                .check(matches(not(isDisplayed())))
        }

        fun verifyTitleIsNotVisible() {
            onView(withId(R.id.postDetailsTitle))
                .check(matches(not(isDisplayed())))
        }

        fun verifyAuthorIsCorrect() {
            onView(withId(R.id.postDetailsAuthor))
                .check(matches(isDisplayed()))
                .check(matches(withText(mockedRedditPost.author)))
        }

        fun verifyTitleIsCorrect() {
            onView(withId(R.id.postDetailsTitle))
                .check(matches(isDisplayed()))
                .check(matches(withText(mockedRedditPost.title)))
        }

        fun verifyImageIsVisible() {
            onView(withId(R.id.postDetailsImageView))
                .check(matches(isDisplayed()))
        }
    }
}
