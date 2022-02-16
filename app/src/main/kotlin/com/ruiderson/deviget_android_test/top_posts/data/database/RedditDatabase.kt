package com.ruiderson.deviget_android_test.top_posts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ruiderson.deviget_android_test.shared.models.RedditPost

@Database(
    entities = [
        RedditPost::class,
        RedditParams::class
    ],
    version = RedditDatabase.DATABASE_VERSION,
    exportSchema = false
)
internal abstract class RedditDatabase : RoomDatabase() {

    abstract fun getRedditPostDao(): RedditPostDao

    abstract fun getRedditParamsDao(): RedditParamsDao

    companion object {
        const val DATABASE_NAME = "reddit"
        const val DATABASE_VERSION = 1
    }
}
