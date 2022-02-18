package com.ruiderson.deviget_android_test.top_posts.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class RedditParams(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "after")
    val after: String?,
    @ColumnInfo(name = "shouldRefresh")
    val shouldRefresh: Boolean
)
