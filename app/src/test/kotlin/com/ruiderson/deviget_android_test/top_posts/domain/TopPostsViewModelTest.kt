package com.ruiderson.deviget_android_test.top_posts.domain

import com.ruiderson.deviget_android_test.shared.models.RedditPost
import com.ruiderson.deviget_android_test.test.rules.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TopPostsViewModelTest {

    @get:Rule
    val coroutinesDispatcherRule = CoroutineTestRule()

    private val useCase: TopPostsUseCase = mockk {
        every { getRedditTopPosts() } returns mockk()
        coEvery { shouldRefresh() } answers { }
        coEvery { markRedditPostAsRead(any()) } answers { }
        coEvery { markRedditPostAsDismissed(any()) } answers { }
        coEvery { dismissAll() } answers { }
    }

    private val viewModel = TopPostsViewModel(useCase)

    @Test
    fun whenGetRedditTopPosts_verifyUseCaseIsCalled() {
        viewModel.getRedditTopPosts()

        verify { useCase.getRedditTopPosts() }
    }

    @Test
    fun whenShouldRefresh_verifyUseCaseIsCalled() = runBlockingTest {
        viewModel.shouldRefresh()

        coVerify { useCase.shouldRefresh() }
    }

    @Test
    fun whenMarkRedditPostAsRead_verifyUseCaseIsCalled() = runBlockingTest {
        val redditPost: RedditPost = mockk()

        viewModel.markRedditPostAsRead(redditPost)

        coVerify { useCase.markRedditPostAsRead(redditPost) }
    }

    @Test
    fun whenMarkRedditPostAsDismissed_verifyUseCaseIsCalled() = runBlockingTest {
        val redditPost: RedditPost = mockk()

        viewModel.markRedditPostAsDismissed(redditPost)

        coVerify { useCase.markRedditPostAsDismissed(redditPost) }
    }

    @Test
    fun whenDismissAll_verifyUseCaseIsCalled() = runBlockingTest {
        viewModel.dismissAll()

        coVerify { useCase.dismissAll() }
    }
}
