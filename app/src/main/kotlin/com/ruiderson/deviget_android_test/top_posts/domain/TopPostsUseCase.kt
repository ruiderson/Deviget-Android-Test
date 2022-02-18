package com.ruiderson.deviget_android_test.top_posts.domain

import com.ruiderson.deviget_android_test.shared.models.RedditPost
import com.ruiderson.deviget_android_test.top_posts.data.RedditRepository

internal class TopPostsUseCase(
    private val repository: RedditRepository
) {

    fun getRedditTopPosts() = repository.getRedditTopPosts(PAGE_SIZE, MAX_SIZE)

    suspend fun shouldRefresh() = repository.shouldRefresh()

    suspend fun markRedditPostAsRead(redditPost: RedditPost) = repository.markRedditPostAsRead(redditPost)

    suspend fun markRedditPostAsDismissed(redditPost: RedditPost) = repository.markRedditPostAsDismissed(redditPost)

    suspend fun dismissAll() = repository.dismissAll()

    companion object {
        private const val PAGE_SIZE = 10
        private const val MAX_SIZE = 50
    }
}
