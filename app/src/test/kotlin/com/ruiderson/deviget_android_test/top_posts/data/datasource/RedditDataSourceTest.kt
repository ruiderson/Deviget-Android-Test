package com.ruiderson.deviget_android_test.top_posts.data.datasource

import androidx.paging.LoadType
import androidx.paging.PagingState
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RedditDataSourceTest {

    private val repository: RedditDataSourceRepository = mockk {
        coEvery { getAfterKey(any()) } returns ""
        coEvery { fetchData(any(), any()) } returns true
    }

    private val dataSource = RedditDataSource(repository)

    @Test
    fun givenRefreshLoadType_whenLoad_verifyGetAfterKeyIsNotCalled() = runBlockingTest {
        val loadType = LoadType.REFRESH
        val state = mockPagingState()

        dataSource.load(loadType, state)

        coVerify(exactly = 0) { repository.getAfterKey(any()) }
    }

    @Test
    fun givenRefreshLoadType_whenLoad_verifyFetchDataIsCalled() = runBlockingTest {
        val loadType = LoadType.REFRESH
        val state = mockPagingState()

        dataSource.load(loadType, state)

        coVerify { repository.fetchData(any(), any()) }
    }

    @Test
    fun givenPrependLoadType_whenLoad_verifyGetAfterKeyIsNotCalled() = runBlockingTest {
        val loadType = LoadType.PREPEND
        val state = mockPagingState()

        dataSource.load(loadType, state)

        coVerify(exactly = 0) { repository.getAfterKey(any()) }
    }

    @Test
    fun givenPrependLoadType_whenLoad_verifyFetchDataIsNotCalled() = runBlockingTest {
        val loadType = LoadType.PREPEND
        val state = mockPagingState()

        dataSource.load(loadType, state)

        coVerify(exactly = 0) { repository.fetchData(any(), any()) }
    }

    @Test
    fun givenAppendLoadType_whenLoad_verifyGetAfterKeyIsCalled() = runBlockingTest {
        val loadType = LoadType.APPEND
        val state = mockPagingState()

        dataSource.load(loadType, state)

        coVerify { repository.getAfterKey(any()) }
    }

    @Test
    fun givenAppendLoadType_whenLoad_verifyFetchDataIsCalled() = runBlockingTest {
        val loadType = LoadType.APPEND
        val state = mockPagingState()

        dataSource.load(loadType, state)

        coVerify { repository.fetchData(any(), any()) }
    }

    @Test
    fun givenAppendLoadType_whenLoad_andGetAfterKeyIsNull_verifyFetchDataIsNotCalled() = runBlockingTest {
        val loadType = LoadType.APPEND
        val state = mockPagingState()
        coEvery { repository.getAfterKey(any()) } returns null

        dataSource.load(loadType, state)

        coVerify(exactly = 0) { repository.fetchData(any(), any()) }
    }

    private fun mockPagingState() = PagingState<Int, RedditPost>(
        anchorPosition = null,
        leadingPlaceholderCount = 0,
        pages = mockk(),
        config = mockk()
    )
}
