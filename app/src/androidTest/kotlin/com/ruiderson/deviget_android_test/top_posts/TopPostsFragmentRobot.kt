package com.ruiderson.deviget_android_test.top_posts

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.ruiderson.deviget_android_test.R
import com.ruiderson.deviget_android_test.base.di.coreModule
import com.ruiderson.deviget_android_test.base.utils.TimestampConverter
import com.ruiderson.deviget_android_test.di.appModule
import com.ruiderson.deviget_android_test.test.di.getKodeinInstance
import com.ruiderson.deviget_android_test.test.di.mockKodeinModule
import com.ruiderson.deviget_android_test.test.espresso.RecyclerViewItemCountAssertion
import com.ruiderson.deviget_android_test.test.espresso.itemAtPosition
import com.ruiderson.deviget_android_test.top_posts.adapter.IdlingResourceRedditPostAdapter
import com.ruiderson.deviget_android_test.top_posts.adapter.RedditPostAdapter
import com.ruiderson.deviget_android_test.top_posts.adapter.RedditPostViewHolder
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditDatabase
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditApi
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditPostDto
import com.ruiderson.deviget_android_test.top_posts.domain.TopPostsViewModel
import io.mockk.mockk
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.hamcrest.Matchers.not
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import com.ruiderson.deviget_android_test.test.espresso.actionOnItemView

class TopPostsFragmentRobot(
    idlingResource: CountingIdlingResource,
    block: TopPostsFragmentRobot.() -> Unit
) {

    private val kodein = getKodeinInstance()

    private val adapter: IdlingResourceRedditPostAdapter = spyk(
        IdlingResourceRedditPostAdapter(idlingResource)
    )

    private val timestampConverter = TimestampConverter(
        ApplicationProvider.getApplicationContext()
    )

    private val redditPostList = mutableListOf<RedditPostDto>().apply {
        for (i in 0 until ITEM_COUNT) {
            add(RedditPostDto(
                id = i.toString(),
                title = "title $i",
                author = "author $i",
                num_comments = i.toString(),
                thumbnail = "thumbnail $i",
                created_utc = "1644946968.0"
            ))
        }
    }

    private val api: RedditApi = mockk {
        coEvery { getRedditTopPosts(any(), any()) } returns mockk {
            every { data.after } returns "after"
            every { getPosts() } returns redditPostList
        }
    }

    private val dataBase: RedditDatabase = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        RedditDatabase::class.java
    ).build()

    private val viewModel: TopPostsViewModel = spyk(TopPostsViewModel(kodein))

    private lateinit var fragmentScenario: FragmentScenario<TopPostsFragment>

    init {
        setup()
        block.invoke(this)
    }

    private fun setup() {
        mockKodeinModule(appModule, coreModule) {
            bind<RedditPostAdapter>(overrides = true) with provider { adapter }
            bind(overrides = true) from provider { api }
            bind(overrides = true) from provider { dataBase }
            bind(overrides = true) from provider { viewModel }
        }
    }

    inner class Arrange(block: Arrange.() -> Unit) {
        init {
            block.invoke(this)
        }

        fun insertValidItemThubmnail(itemPosition: Int) {
            redditPostList.add(
                itemPosition,
                RedditPostDto(
                    id = "custom item",
                    title = "custom title",
                    author = "custom author",
                    num_comments = "0",
                    thumbnail = "https://play-lh.googleusercontent.com/8Vw-7MAm558750a4M55fiOlUf7lP2cYnFuqSWynrygIiyEEiQQDa_xxHKYOX83L0UD2T=s180",
                    created_utc = "1644946968.0"
                )
            )
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

        fun performSwipeRefresh() {
            onView(withId(R.id.topEntriesSwipeRefresh))
                .perform(swipeDown())
        }

        fun performDismissAllClick() {
            onView(withId(R.id.dismissAllButton))
                .perform(click())
        }

        fun performScrollAtPosition(itemPosition: Int) {
            onView(withId(R.id.topEntriesRecyclerView))
                .perform(scrollToPosition<RedditPostViewHolder>(itemPosition))
        }

        fun performItemDismissButtonClick(itemPosition: Int) {
            onView(withId(R.id.topEntriesRecyclerView))
                .perform(actionOnItemAtPosition<RedditPostViewHolder>(
                    itemPosition,
                    actionOnItemView(R.id.redditPostDismissButton)
                ))
        }

        fun performClickAtPosition(itemPosition: Int) {
            onView(withId(R.id.topEntriesRecyclerView))
                .perform(actionOnItemAtPosition<RedditPostViewHolder>(
                    itemPosition,
                    click()
                ))
        }
    }

    inner class Assert(block: Assert.() -> Unit) {
        init {
            block.invoke(this)
        }

        fun verifyGetRedditTopPostsIsCalled() {
            verify { viewModel.getRedditTopPosts() }
        }

        fun verifyAdapterSubmitDataIsCalled() {
            coVerify { adapter.submitData(any()) }
        }

        fun verifyItemCountIsCorrect() {
            onView(withId(R.id.topEntriesRecyclerView))
                .check(RecyclerViewItemCountAssertion(ITEM_COUNT))
        }

        fun verifyShouldRefreshIsCalled() {
            verify { viewModel.shouldRefresh() }
        }

        fun verifyAdapterRefreshIsCalled() {
            verify { adapter.refresh() }
        }

        fun verifyDismissAllIsCalled() {
            verify { viewModel.dismissAll() }
        }

        fun verifyItemIsUnread(itemPosition: Int) {
            onView(withId(R.id.topEntriesRecyclerView))
                .check(matches(itemAtPosition(
                    itemPosition,
                    isDisplayed(),
                    R.id.isUnreadView
                )))
        }

        fun verifyItemAuthorIsCorrect(itemPosition: Int) {
            val value = redditPostList[itemPosition].author

            onView(withId(R.id.topEntriesRecyclerView))
                .check(matches(itemAtPosition(
                    itemPosition,
                    withText(value),
                    R.id.redditPostAuthor
                )))
        }

        fun verifyItemEntryDateIsCorrect(itemPosition: Int) {
            val value = timestampConverter.fromString(
                redditPostList[itemPosition].created_utc
            )

            onView(withId(R.id.topEntriesRecyclerView))
                .check(matches(itemAtPosition(
                    itemPosition,
                    withText(value),
                    R.id.redditPostEntryDate
                )))
        }

        fun verifyItemThumbnailIsDisplayed(itemPosition: Int) {
            onView(withId(R.id.topEntriesRecyclerView))
                .check(matches(itemAtPosition(
                    itemPosition,
                    isDisplayed(),
                    R.id.redditPostImageView
                )))
        }

        fun verifyItemThumbnailIsNotDisplayed(itemPosition: Int) {
            onView(withId(R.id.topEntriesRecyclerView))
                .check(matches(itemAtPosition(
                    itemPosition,
                    not(isDisplayed()),
                    R.id.redditPostImageView
                )))
        }

        fun verifyItemTitleIsCorrect(itemPosition: Int) {
            val value = redditPostList[itemPosition].title

            onView(withId(R.id.topEntriesRecyclerView))
                .check(matches(itemAtPosition(
                    itemPosition,
                    withText(value),
                    R.id.redditPostTitle
                )))
        }

        fun verifyItemCommentsIsCorrect(itemPosition: Int) {
            val value = "${redditPostList[itemPosition].num_comments} comments"

            onView(withId(R.id.topEntriesRecyclerView))
                .check(matches(itemAtPosition(
                    itemPosition,
                    withText(value),
                    R.id.redditPostComments
                )))
        }

        fun verifyItemDismissIsCalled() {
            verify { viewModel.markRedditPostAsDismissed(any()) }
        }

        fun verifyMarkRedditPostAsReadIsCalled() {
            verify { viewModel.markRedditPostAsRead(any()) }
        }
    }

    companion object {
        private const val ITEM_COUNT = 10
    }
}
