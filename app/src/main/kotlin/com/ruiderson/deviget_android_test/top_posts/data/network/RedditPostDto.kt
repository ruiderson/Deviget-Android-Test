package com.ruiderson.deviget_android_test.top_posts.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RedditPostDto(
    @SerialName("name")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("author")
    val author: String,
    @SerialName("num_comments")
    val num_comments: String,
    @SerialName("thumbnail")
    val thumbnail: String?,
    @SerialName("created_utc")
    val created_utc: String,
    val isUnread: Boolean = true
)
