package com.ruiderson.deviget_android_test.top_posts.data.network

import com.ruiderson.deviget_android_test.network.reddit.RedditClient

internal class RedditApi(
    private val client: RedditClient
) {

    suspend fun getRedditTopPosts(
        limit: Int,
        after: String?
    ): RedditTopPostsDto = client.get(
        path = "subreddit/hot.json?limit=${limit}${getAfterQuery(after)}"
    )

    private fun getAfterQuery(after: String?) = after?.let {
        "&after=${after}"
    } ?: ""
}
