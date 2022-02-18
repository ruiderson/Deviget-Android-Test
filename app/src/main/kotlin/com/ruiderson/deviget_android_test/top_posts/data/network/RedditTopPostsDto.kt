package com.ruiderson.deviget_android_test.top_posts.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RedditTopPostsDto(
    @SerialName("data")
    val data: RedditRootData
) {

    fun getPosts(): List<RedditPostDto> = data.children.map {
        it.data
    }
}

@Serializable
internal data class RedditRootData(
    @SerialName("after")
    val after: String?,
    @SerialName("children")
    val children: List<RedditChildrenData>
)

@Serializable
internal data class RedditChildrenData(
    @SerialName("data")
    val data: RedditPostDto
)
