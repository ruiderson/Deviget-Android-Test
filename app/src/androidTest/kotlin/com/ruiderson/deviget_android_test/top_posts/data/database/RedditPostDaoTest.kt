package com.ruiderson.deviget_android_test.top_posts.data.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RedditPostDaoTest {

    private lateinit var dao: RedditPostDao

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
    fun whenGetAll_verifyItemListIsReturned() = runBlocking {
        val itemCount = 5
        val itemList = mockRedditPostList(itemCount)
        dao.insertAll(itemList)

        val result = dao.getAll()
        assertEquals(result.size, itemCount)
    }

    @Test
    fun whenGetItemCount_verifyValueIsCorrect() = runBlocking {
        val itemCount = 10
        val itemList = mockRedditPostList(itemCount)
        dao.insertAll(itemList)

        val result = dao.getItemCount()
        assertEquals(result, itemCount)
    }

    @Test
    fun whenInsert_verifyListIsInsertedWithSuccess() = runBlocking {
        val itemCount = 3
        val itemList = mockRedditPostList(itemCount)

        dao.insertAll(itemList)
    }

    @Test
    fun whenUpdate_verifyItemIsUpdated() = runBlocking {
        val itemList = mockRedditPostList(3)
        dao.insertAll(itemList)

        val itemPosition = 2
        val item = itemList[itemPosition]
        item.isUnread = false
        dao.update(item)

        val result = dao.getAll()
        val updatedItem = result[itemPosition]

        assertEquals(item.id, updatedItem.id)
        assertTrue(!updatedItem.isUnread)
    }

    @Test
    fun whenDismissAll_verifyItemListIsEmpty() = runBlocking {
        val itemCount = 7
        val itemList = mockRedditPostList(itemCount)
        dao.insertAll(itemList)

        dao.dismissAll()

        val result = dao.getAll()
        assertTrue(result.isEmpty())
    }

    @Test
    fun whenDeleteAll_verifyItemListIsEmpty() = runBlocking {
        val itemCount = 7
        val itemList = mockRedditPostList(itemCount)
        dao.insertAll(itemList)

        dao.deleteAll()

        val result = dao.getAll()
        assertTrue(result.isEmpty())
    }

    private fun mockRedditPostList(itemCount: Int) = mutableListOf<RedditPost>().apply {
        for (i in 0 until itemCount) {
            add(
                RedditPost(
                    id = i.toString(),
                    title = "title $i",
                    author = "author $i",
                    num_comments = i.toString(),
                    thumbnail = "thum $i",
                    created_utc = "create $i",
                    isUnread = true,
                    isDismissed = false
                )
            )
        }
    }

    private fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RedditDatabase::class.java
        ).build()
        dao = database.getRedditPostDao()
    }
}
