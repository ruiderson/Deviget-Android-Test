package com.ruiderson.deviget_android_test.top_posts.domain

import com.ruiderson.deviget_android_test.shared.models.RedditPost
import com.ruiderson.deviget_android_test.top_posts.data.RedditRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class TopPostsUseCaseTest {

    private val repository: RedditRepository = mockk {
        every { getRedditTopPosts(any(), any()) } returns mockk()
        coEvery { shouldRefresh() } answers { }
        coEvery { markRedditPostAsRead(any()) } answers { }
        coEvery { markRedditPostAsDismissed(any()) } answers { }
        coEvery { dismissAll() } answers { }
    }

    private val useCase = TopPostsUseCase(repository)

    @Test
    fun whenGetRedditTopPosts_verifyRepositoryIsCalled() {
        val pageSize = 10
        val maxItemSize = 50

        useCase.getRedditTopPosts()

        verify { repository.getRedditTopPosts(pageSize, maxItemSize) }
    }

    @Test
    fun whenShouldRefresh_verifyRepositoryIsCalled() = runBlockingTest {
        useCase.shouldRefresh()

        coVerify { repository.shouldRefresh() }
    }

    @Test
    fun whenMarkRedditPostAsRead_verifyRepositoryIsCalled() = runBlockingTest {
        val redditPost: RedditPost = mockk()

        useCase.markRedditPostAsRead(redditPost)

        coVerify { repository.markRedditPostAsRead(redditPost) }
    }

    @Test
    fun whenMarkRedditPostAsDismissed_verifyRepositoryIsCalled() = runBlockingTest {
        val redditPost: RedditPost = mockk()

        useCase.markRedditPostAsDismissed(redditPost)

        coVerify { repository.markRedditPostAsDismissed(redditPost) }
    }

    @Test
    fun whenDismissAll_verifyRepositoryIsCalled() = runBlockingTest {
        useCase.dismissAll()

        coVerify { repository.dismissAll() }
    }
}
