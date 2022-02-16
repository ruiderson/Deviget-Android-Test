package com.ruiderson.deviget_android_test.top_posts.data

import com.ruiderson.deviget_android_test.shared.models.RedditPost
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditDatabase
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditParamsDao
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditPostDao
import com.ruiderson.deviget_android_test.top_posts.data.datasource.RedditPagingSourceFactory
import io.mockk.every
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class RedditRepositoryTest {

    private val pagingSourceFactory: RedditPagingSourceFactory = mockk {
        every { create(any(), any()) } returns mockk()
    }

    private val redditParamsDao: RedditParamsDao = mockk {
        coEvery { shouldRefresh() } answers { }
    }

    private val redditPostDao: RedditPostDao = mockk {
        coEvery { getPagingSource() } returns mockk()
        coEvery { update(any()) } answers { }
        coEvery { dismissAll() } answers { }
    }

    private val dataBase: RedditDatabase = mockk {
        coEvery { getRedditPostDao() } returns redditPostDao
        coEvery { getRedditParamsDao() } returns redditParamsDao
    }

    private val repository = RedditRepository(
        pagingSourceFactory,
        dataBase
    )

    @Test
    fun whenGetRedditTopPosts_verifyDataSourceFactoryIsCalled() {
        val pageSize = 10
        val maxItemSize = 50

        repository.getRedditTopPosts(pageSize, maxItemSize)

        verify { pagingSourceFactory.create(pageSize, maxItemSize) }
    }

    @Test
    fun whenShouldRefresh_verifyRedditParamsDaoIsCalled() = runBlockingTest {
        repository.shouldRefresh()

        coVerify { redditParamsDao.shouldRefresh() }
    }

    @Test
    fun whenMarkRedditPostAsRead_verifyRedditPostDaoIsCalled() = runBlockingTest {
        val redditPost = mockRedditPost()

        repository.markRedditPostAsRead(redditPost)

        coVerify { redditPostDao.update(redditPost) }
    }

    @Test
    fun whenMarkRedditPostAsRead_verifyValueChanged() = runBlockingTest {
        val redditPost = mockRedditPost()

        repository.markRedditPostAsRead(redditPost)

        assertEquals(redditPost.isUnread, false)
    }

    @Test
    fun whenMarkRedditPostAsDismissed_verifyRedditPostDaoIsCalled() = runBlockingTest {
        val redditPost = mockRedditPost()

        repository.markRedditPostAsDismissed(redditPost)

        coVerify { redditPostDao.update(redditPost) }
    }

    @Test
    fun whenMarkRedditPostAsDismissed_verifyValueChanged() = runBlockingTest {
        val redditPost = mockRedditPost()

        repository.markRedditPostAsDismissed(redditPost)

        assertEquals(redditPost.isDismissed, true)
    }

    @Test
    fun whenDismissAll_verifyRedditPostDaoIsCalled() = runBlockingTest {
        repository.dismissAll()

        coVerify { redditPostDao.dismissAll() }
    }

    private fun mockRedditPost() = RedditPost(
        id = "",
        title = "title",
        author = "author",
        num_comments = "",
        thumbnail = "thum",
        created_utc = "create",
        entry_date = "",
        isUnread = true,
        isDismissed = false
    )
}
