package com.ruiderson.deviget_android_test.shared.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class RedditPost(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "num_comments")
    val num_comments: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String?,
    @ColumnInfo(name = "created_utc")
    val created_utc: String,
    @ColumnInfo(name = "entry_date")
    val entry_date: String,
    @ColumnInfo(name = "isUnread")
    var isUnread: Boolean = true,
    @ColumnInfo(name = "isDismissed")
    var isDismissed: Boolean = false
)
