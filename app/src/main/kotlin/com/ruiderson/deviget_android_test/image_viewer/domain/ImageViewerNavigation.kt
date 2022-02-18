package com.ruiderson.deviget_android_test.image_viewer.domain

import android.content.Context
import com.ruiderson.deviget_android_test.base.utils.IntentFactory
import com.ruiderson.deviget_android_test.image_viewer.ImageViewerActivity
import com.ruiderson.deviget_android_test.shared.models.RedditPost

internal class ImageViewerNavigation(
    private val intentFactory: IntentFactory
) {

    fun navigateToImageViewer(
        context: Context,
        redditPost: RedditPost
    ) = with(context) {
        val intent = intentFactory.create(ImageViewerActivity::class.java).apply {
            putExtra(REDDIT_POST_EXTRA, redditPost)
        }
        startActivity(intent)
    }

    companion object {
        const val REDDIT_POST_EXTRA = "REDDIT_POST_EXTRA"
    }
}
