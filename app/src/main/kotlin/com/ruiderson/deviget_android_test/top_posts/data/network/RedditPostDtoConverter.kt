package com.ruiderson.deviget_android_test.top_posts.data.network

import com.ruiderson.deviget_android_test.base.utils.TimestampConverter
import com.ruiderson.deviget_android_test.shared.models.RedditPost

internal class RedditPostDtoConverter(
    private val timestampConverter: TimestampConverter
) {

    fun convert(redditPostDto: RedditPostDto): RedditPost = RedditPost(
        id = redditPostDto.id,
        title = redditPostDto.title,
        author = redditPostDto.author,
        num_comments = redditPostDto.num_comments,
        thumbnail = redditPostDto.thumbnail,
        created_utc = redditPostDto.created_utc,
        entry_date = timestampConverter.fromString(redditPostDto.created_utc),
        isUnread = redditPostDto.isUnread
    )

    fun convertAll(postsList: List<RedditPostDto>) = postsList.map {
        convert(it)
    }
}
