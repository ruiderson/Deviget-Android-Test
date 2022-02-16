package com.ruiderson.deviget_android_test.top_posts.data.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RedditParamsDaoTest {

    private lateinit var dao: RedditParamsDao

    private lateinit var database: RedditDatabase

    @Before
    fun setup() {
        setupDatabase()
    }

    @After
    @Throws(IOException::class)
    fun finish() {
        database.close()
    }

    @Test
    fun whenGetParams_verifyItemIsReturned() = runBlocking {
        val redditParams = mockRedditParams()
        dao.insert(redditParams)

        val result = dao.getParams()
        assertTrue(result != null)
        assertEquals(result?.id, redditParams.id)
    }

    @Test
    fun whenShouldRefresh_verifyItemIsUpdated() = runBlocking {
        val redditParams = mockRedditParams()
        dao.insert(redditParams)

        dao.shouldRefresh()

        val result = dao.getParams()!!
        assertTrue(result.shouldRefresh)
    }

    @Test
    fun whenInsert_verifyItemIsInsertedWithSuccess() = runBlocking {
        val redditParams = mockRedditParams()

        dao.insert(redditParams)
    }

    @Test
    fun whenDelete_verifyItemIsDeleted() = runBlocking {
        val redditParams = mockRedditParams()
        dao.insert(redditParams)

        dao.delete()

        val result = dao.getParams()
        assertTrue(result == null)
    }

    private fun mockRedditParams() = RedditParams(
        id = RedditParamsDao.PAGING_KEY_ID,
        after = null,
        shouldRefresh = false
    )

    private fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RedditDatabase::class.java
        ).build()
        dao = database.getRedditParamsDao()
    }
}
