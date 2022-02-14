package com.ruiderson.deviget_android_test.top_posts.data.network

import org.junit.Assert.assertEquals
import org.junit.Test

class RedditPostDtoConverterTest {

    private val converter = RedditPostDtoConverter()

    @Test
    fun whenConvert_verifyCorrectValueIsReturned() {
        val dtoItem = mockRedditPostDto()

        val result = converter.convert(dtoItem)

        assertEquals(result.id, dtoItem.id)
    }

    private fun mockRedditPostDto() = RedditPostDto(
        id = "id",
        title = "title",
        author = "author",
        num_comments = "num_comments",
        thumbnail = "thumbnail",
        created_utc = "created_utc"
    )
}
