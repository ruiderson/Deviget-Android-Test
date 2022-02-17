package com.ruiderson.deviget_android_test.image_viewer

import android.app.Activity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ruiderson.deviget_android_test.base.extensions.viewBinding
import com.ruiderson.deviget_android_test.databinding.ActivityImageViewerBinding
import com.ruiderson.deviget_android_test.image_viewer.domain.ImageViewerNavigation
import com.ruiderson.deviget_android_test.shared.models.RedditPost

internal class ImageViewerActivity : Activity() {

    private val viewBinding: ActivityImageViewerBinding by viewBinding()

    private var redditPost: RedditPost? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadExtras()
        setupViews()
    }

    private fun setupViews() = with(viewBinding) {
        toolbar.setNavigationOnClickListener {
            finish()
        }

        redditPost?.let {
            toolbar.title = it.title
            Glide
                .with(imageViewer.context)
                .load(it.thumbnail)
                .into(imageViewer)
        }
    }

    private fun loadExtras() = with(intent) {
        redditPost = getParcelableExtra(ImageViewerNavigation.REDDIT_POST_EXTRA)
    }
}
