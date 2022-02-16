package com.ruiderson.deviget_android_test.top_posts.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.ruiderson.deviget_android_test.shared.models.RedditPost

@Dao
internal interface RedditPostDao {

    @Query("SELECT * FROM RedditPost WHERE isDismissed = 0")
    fun getPagingSource(): PagingSource<Int, RedditPost>

    @Query("SELECT * FROM RedditPost WHERE isDismissed = 0")
    fun getAll(): List<RedditPost>

    @Query("SELECT COUNT(id) FROM RedditPost")
    suspend fun getItemCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(redditPostEntityList: List<RedditPost>)

    @Update
    suspend fun update(redditPostEntity: RedditPost)

    @Query("UPDATE RedditPost SET isDismissed = 1")
    suspend fun dismissAll()

    @Query("DELETE FROM RedditPost")
    suspend fun deleteAll()
}
