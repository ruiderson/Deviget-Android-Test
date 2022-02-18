package com.ruiderson.deviget_android_test.top_posts.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
internal interface RedditParamsDao {

    @Query("SELECT * FROM RedditParams WHERE id = $PAGING_KEY_ID")
    suspend fun getParams(): RedditParams?

    @Query("UPDATE RedditParams SET shouldRefresh = 1")
    suspend fun shouldRefresh()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(params: RedditParams)

    @Query("DELETE FROM RedditParams WHERE id = $PAGING_KEY_ID")
    suspend fun delete()

    companion object {
        const val PAGING_KEY_ID = 1
    }
}
