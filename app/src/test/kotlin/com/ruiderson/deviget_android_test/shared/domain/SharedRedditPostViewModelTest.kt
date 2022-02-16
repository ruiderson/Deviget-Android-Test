package com.ruiderson.deviget_android_test.shared.domain

import com.ruiderson.deviget_android_test.shared.models.RedditPost
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.Assert.assertTrue

class SharedRedditPostViewModelTest {

    private val viewModel = SharedRedditPostViewModel()

    @Test
    fun whenInit_verifyStateIsOnHidePostDetails() {
        val state = viewModel.redditPostState.value

        assertTrue(state is SharedRedditPostState.OnHidePostDetails)
    }

    @Test
    fun whenOnRedditPostClicked_verifyStateIsOnShowPostDetails() {
        val redditPost = mockRedditPost()

        viewModel.onRedditPostClicked(redditPost)
        val state = viewModel.redditPostState.value

        assertTrue(state is SharedRedditPostState.OnShowPostDetails)
    }

    @Test
    fun givenSamePostId_whenOnRedditPostDismissed_verifyStateIsOnHidePostDetails() {
        val redditPost = mockRedditPost()
        viewModel.onRedditPostClicked(redditPost)

        viewModel.onRedditPostDismissed(redditPost)
        val state = viewModel.redditPostState.value

        assertTrue(state is SharedRedditPostState.OnHidePostDetails)
    }

    @Test
    fun givenDifferentPostId_whenOnRedditPostDismissed_verifyStateDoesNotChange() {
        viewModel.onRedditPostClicked(mockRedditPost())

        viewModel.onRedditPostDismissed(mockRedditPost("1"))
        val state = viewModel.redditPostState.value

        assertTrue(state is SharedRedditPostState.OnShowPostDetails)
    }

    @Test
    fun whenOnDismissAll_verifyStateIsOnHidePostDetails() {
        viewModel.onDismissAll()
        val state = viewModel.redditPostState.value

        assertTrue(state is SharedRedditPostState.OnHidePostDetails)
    }

    private fun mockRedditPost(mockId: String = "id"): RedditPost = mockk {
        every { id } returns mockId
    }
}
