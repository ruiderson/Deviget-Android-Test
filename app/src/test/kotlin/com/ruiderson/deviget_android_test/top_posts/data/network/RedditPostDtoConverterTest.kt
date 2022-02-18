package com.ruiderson.deviget_android_test.top_posts.data.network

import com.ruiderson.deviget_android_test.base.utils.TimestampConverter
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class RedditPostDtoConverterTest {

    private val timestampConverter: TimestampConverter = mockk {
        every { fromString(any()) } returns ""
    }

    private val converter = RedditPostDtoConverter(timestampConverter)

    @Test
    fun whenConvert_verifyCorrectValueIsReturned() {
        val dtoItem = mockRedditPostDto()

        val result = converter.convert(dtoItem)

        assertEquals(result.id, dtoItem.id)
    }

    @Test
    fun whenConvert_verifyTimestampConverterIsCalled() {
        val dtoItem = mockRedditPostDto()

        converter.convert(dtoItem)

        verify { timestampConverter.fromString(any()) }
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
