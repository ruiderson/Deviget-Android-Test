package com.ruiderson.deviget_android_test.top_posts.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditDatabase

@OptIn(ExperimentalPagingApi::class)
internal class RedditPagingSourceFactory(
    private val repository: RedditDataSourceRepository,
    private val dataBase: RedditDatabase
) {

    fun create(
        pageSize: Int,
        maxItemSize: Int
    ) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            maxSize = maxItemSize
        ),
        remoteMediator = RedditDataSource(
            repository
        )
    ) {
        dataBase.getRedditPostDao().getPagingSource()
    }.flow
}
