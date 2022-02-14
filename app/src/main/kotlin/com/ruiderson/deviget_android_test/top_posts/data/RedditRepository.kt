package com.ruiderson.deviget_android_test.top_posts.data

import com.ruiderson.deviget_android_test.shared.models.RedditPost
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditDatabase
import com.ruiderson.deviget_android_test.top_posts.data.datasource.RedditPagingSourceFactory

internal class RedditRepository(
    private val pagingSourceFactory: RedditPagingSourceFactory,
    private val dataBase: RedditDatabase
) {

    fun getRedditTopPosts(
        pageSize: Int,
        maxItemSize: Int
    ) = pagingSourceFactory.create(pageSize, maxItemSize)

    suspend fun shouldRefresh() = dataBase.getRedditParamsDao().shouldRefresh()

    suspend fun markRedditPostAsRead(redditPost: RedditPost) = with(redditPost) {
        isUnread = false
        dataBase.getRedditPostDao().update(this)
    }

    suspend fun markRedditPostAsDismissed(redditPost: RedditPost) = with(redditPost) {
        isDismissed = true
        dataBase.getRedditPostDao().update(this)
    }

    suspend fun dismissAll() = dataBase.getRedditPostDao().dismissAll()
}
