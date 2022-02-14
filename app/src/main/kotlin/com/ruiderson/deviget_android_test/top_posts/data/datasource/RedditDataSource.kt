package com.ruiderson.deviget_android_test.top_posts.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.ruiderson.deviget_android_test.network.core.HttpException
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
internal class RedditDataSource(
    private val repository: RedditDataSourceRepository
) : RemoteMediator<Int, RedditPost>(){

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RedditPost>
    ): MediatorResult = when (loadType) {

        LoadType.REFRESH -> loadData(null, state.config.pageSize)

        LoadType.PREPEND -> MediatorResult.Success(
            endOfPaginationReached = true
        )

        LoadType.APPEND -> repository.getAfterKey(state.config.maxSize)?.let {
            loadData(it, state.config.pageSize)
        } ?: MediatorResult.Success(
            endOfPaginationReached = true
        )
    }

    private suspend fun loadData(
        after: String?,
        pageSize: Int
    ) = try {

        val endOfPaginationReached = repository.fetchData(after, pageSize)
        MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

    }catch (e: IOException) {
        MediatorResult.Error(e)
    } catch (e: HttpException) {
        MediatorResult.Error(e)
    }

    override suspend fun initialize() = InitializeAction.LAUNCH_INITIAL_REFRESH
}
