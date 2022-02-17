package com.ruiderson.deviget_android_test.image_viewer.domain

import android.content.Context
import android.content.Intent
import com.ruiderson.deviget_android_test.image_viewer.ImageViewerActivity
import com.ruiderson.deviget_android_test.shared.models.RedditPost

internal class ImageViewerNavigation {

    fun navigateToImageViewer(
        context: Context,
        redditPost: RedditPost
    ) = with(context) {
        val intent = Intent(context, ImageViewerActivity::class.java).apply {
            putExtra(REDDIT_POST_EXTRA, redditPost)
        }
        startActivity(intent)
    }

    companion object {
        const val REDDIT_POST_EXTRA = "REDDIT_POST_EXTRA"
    }
}
