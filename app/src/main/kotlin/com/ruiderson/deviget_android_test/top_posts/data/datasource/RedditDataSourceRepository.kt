package com.ruiderson.deviget_android_test.top_posts.data.datasource

import androidx.room.withTransaction
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditDatabase
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditParams
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditParamsDao
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditPostDao
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditApi
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditPostDtoConverter
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditTopPostsDto

internal class RedditDataSourceRepository(
    private val converter: RedditPostDtoConverter,
    private val dataBase: RedditDatabase,
    private val api: RedditApi
) {

    suspend fun getAfterKey(maxItemSize: Int): String? {
        dataBase.getRedditPostDao().let {
            if (it.getItemCount() > maxItemSize) return null
        }

        return dataBase.getRedditParamsDao().getParams()?.after
    }

    suspend fun fetchData(
        after: String?,
        pageSize: Int
    ): Boolean {
        val redditTopPosts = api.getRedditTopPosts(pageSize, after)

        dataBase.withTransaction {
            val redditParamsDao = dataBase.getRedditParamsDao()
            val redditPostDao = dataBase.getRedditPostDao()

            val params = redditParamsDao.getParams()
            if (params == null || params.shouldRefresh) {
                redditParamsDao.delete()
                redditPostDao.deleteAll()
            }

            insertRedditTopPosts(
                redditPostDao,
                redditTopPosts
            )

            saveRedditParams(
                redditParamsDao,
                redditTopPosts.data.after
            )
        }

        return redditTopPosts.data.after == null
    }

    private suspend fun insertRedditTopPosts(
        redditPostDao: RedditPostDao,
        RedditTopPosts: RedditTopPostsDto
    ) = redditPostDao.insertAll(
        converter.convertAll(RedditTopPosts.getPosts())
    )

    private suspend fun saveRedditParams(
        redditParamsDao: RedditParamsDao,
        after: String?
    ) = redditParamsDao.insert(
        RedditParams(
            id = RedditParamsDao.PAGING_KEY_ID,
            after = after,
            shouldRefresh = false
        )
    )
}
