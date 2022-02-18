package com.ruiderson.deviget_android_test.top_posts.data.datasource

import androidx.room.withTransaction
import com.ruiderson.deviget_android_test.R
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditDatabase
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditParams
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditParamsDao
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditPostDao
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditApi
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditPostDtoConverter
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditTopPostsDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.MockKAnnotations
import io.mockk.mockkStatic
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RedditDataSourceRepositoryTest {

    private val redditPostDao: RedditPostDao = mockk {
        coEvery { getItemCount() } returns MAX_ITEM_SIZE - 1
        coEvery { insertAll(any()) } answers {  }
        coEvery { deleteAll() } answers {  }
    }

    private val redditParamsDao: RedditParamsDao = mockk {
        val redditParams: RedditParams = mockk {
            every { after } returns "123"
            every { shouldRefresh } returns false
        }
        coEvery { getParams() } returns redditParams
        coEvery { insert(any()) } answers {  }
        coEvery { delete() } answers {  }
    }

    private val dataBase: RedditDatabase = mockk {
        every { getRedditPostDao() } returns redditPostDao
        every { getRedditParamsDao() } returns redditParamsDao
    }

    private val redditTopPostsDto: RedditTopPostsDto = mockk {
        every { data.after } returns "123"
        every { getPosts() } returns mockk()
    }

    private val converter: RedditPostDtoConverter = mockk {
        every { convert(any()) } returns mockk()
        every { convertAll(any()) } returns mockk()
    }

    private val api: RedditApi = mockk {
        coEvery { getRedditTopPosts(any(), any()) } returns redditTopPostsDto
    }

    private val repository = RedditDataSourceRepository(
        converter,
        dataBase,
        api
    )

    @Before
    fun setup() {
        mockDatabaseTransaction()
    }

    @Test
    fun givenReachedMaxItemSize_whenGetAfterKey_verifyReturnsNull() = runBlockingTest {
        val currentItemCount = MAX_ITEM_SIZE + 1
        coEvery { redditPostDao.getItemCount() } returns currentItemCount

        val result = repository.getAfterKey(MAX_ITEM_SIZE)

        assertEquals(result, null)
    }

    @Test
    fun givenUnreachedMaxItemSize_whenGetAfterKey_verifyReturnsCorrectValue() = runBlockingTest {
        val after = "13"
        val redditParams = RedditParams(
            id = RedditParamsDao.PAGING_KEY_ID,
            after = after,
            shouldRefresh = false
        )
        coEvery { redditParamsDao.getParams() } returns redditParams

        val result = repository.getAfterKey(MAX_ITEM_SIZE)

        assertEquals(result, after)
    }

    @Test
    fun whenFetchData_verifyApiIsCalled() = runBlockingTest {
        val after = "123"
        val pageSize = 15

        repository.fetchData(after, pageSize)

        coVerify { api.getRedditTopPosts(pageSize, after) }
    }

    @Test
    fun whenFetchData_andRedditParamsIsNull_verifyDatabaseIsRefreshed() = runBlockingTest {
        val redditParams: RedditParams? = null
        coEvery { redditParamsDao.getParams() } returns redditParams

        repository.fetchData("", 1)

        coVerify { redditParamsDao.delete() }
        coVerify { redditPostDao.deleteAll() }
    }

    @Test
    fun whenFetchData_andShouldRefreshIsTrue_verifyDatabaseIsRefreshed() = runBlockingTest {
        val redditParams: RedditParams = mockk {
            every { shouldRefresh } returns true
        }
        coEvery { redditParamsDao.getParams() } returns redditParams

        repository.fetchData("", 1)

        coVerify { redditParamsDao.delete() }
        coVerify { redditPostDao.deleteAll() }
    }

    @Test
    fun whenFetchData_andShouldRefreshIsFalse_verifyDatabaseIsNotRefreshed() = runBlockingTest {
        val redditParams: RedditParams = mockk {
            every { shouldRefresh } returns false
        }
        coEvery { redditParamsDao.getParams() } returns redditParams

        repository.fetchData("", 1)

        coVerify(exactly = 0) { redditParamsDao.delete() }
        coVerify(exactly = 0) { redditPostDao.deleteAll() }
    }

    @Test
    fun whenFetchData_verifyRedditPostDaoInsertAllIsCalled() = runBlockingTest {
        repository.fetchData("", 1)

        coVerify { redditPostDao.insertAll(any()) }
    }

    @Test
    fun whenFetchData_verifyRedditParamsDaoInsertIsCalled() = runBlockingTest {
        repository.fetchData("", 1)

        coVerify { redditParamsDao.insert(any()) }
    }

    @Test
    fun whenFetchData_andRedditTopPostsHasAfterKey_verifyReturnsFalse() = runBlockingTest {
        val result = repository.fetchData("", 1)

        assertTrue(!result)
    }

    @Test
    fun whenFetchData_andRedditTopPostsHasNoAfterKey_verifyReturnsTrue() = runBlockingTest {
        every { redditTopPostsDto.data.after } returns null

        val result = repository.fetchData("", 1)

        assertTrue(result)
    }

    private fun mockDatabaseTransaction() {
        MockKAnnotations.init(this)
        mockkStatic("androidx.room.RoomDatabaseKt")

        val transactionLambda = slot<suspend () -> R>()
        coEvery { dataBase.withTransaction(capture(transactionLambda)) } coAnswers {
            transactionLambda.captured.invoke()
        }
    }

    companion object {
        private const val MAX_ITEM_SIZE = 50
    }
}
