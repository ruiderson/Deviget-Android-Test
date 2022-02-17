package com.ruiderson.deviget_android_test.image_viewer.domain

import android.content.Context
import android.content.Intent
import com.ruiderson.deviget_android_test.base.utils.IntentFactory
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class ImageViewerNavigationTest {

    private val context: Context = mockk {
        every { startActivity(any()) } answers { }
    }

    private val intent: Intent = mockk()

    private val intentFactory: IntentFactory = mockk {
        every { create(any()) } returns intent
    }

    private val navigation = ImageViewerNavigation(intentFactory)

    @Test
    fun whenNavigateToImageViewer_verifyStartActivityIsCalled() {
        val redditPost: RedditPost = mockk()
        every { intent.putExtra(ImageViewerNavigation.REDDIT_POST_EXTRA, redditPost) } returns intent

        navigation.navigateToImageViewer(context, redditPost)

        verify { context.startActivity(any()) }
    }

    @Test
    fun whenNavigateToImageViewer_verifyIntentExtraIsCorrect() {
        val redditPost: RedditPost = mockk()
        every { intent.putExtra(ImageViewerNavigation.REDDIT_POST_EXTRA, redditPost) } returns intent

        navigation.navigateToImageViewer(context, redditPost)

        verify { intent.putExtra(ImageViewerNavigation.REDDIT_POST_EXTRA, redditPost) }
    }
}
