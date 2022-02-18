package com.ruiderson.deviget_android_test.image_viewer

import android.os.Bundle
import com.bumptech.glide.Glide
import com.ruiderson.deviget_android_test.R
import com.ruiderson.deviget_android_test.base.extensions.viewBinding
import com.ruiderson.deviget_android_test.databinding.ActivityImageViewerBinding
import com.ruiderson.deviget_android_test.image_viewer.domain.ImageViewerNavigation
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import androidx.appcompat.app.AppCompatActivity
import com.ruiderson.deviget_android_test.base.activity.PermissionRequest
import com.ruiderson.deviget_android_test.base.extensions.viewModels
import com.ruiderson.deviget_android_test.image_viewer.domain.ImageViewerViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

internal class ImageViewerActivity : AppCompatActivity(),
    KodeinAware
{

    override val kodein: Kodein by kodein()

    private val viewBinding: ActivityImageViewerBinding by viewBinding()

    private val viewModel: ImageViewerViewModel by viewModels()

    private lateinit var accessMediaPermissionRequest: PermissionRequest

    private var redditPost: RedditPost? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadExtras()

        setupPermissions()
        setupMenuItems()
        setupViews()
    }

    private fun setupPermissions() {
        accessMediaPermissionRequest = viewModel.createAccessMediaPermissionRequest(this)
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

    private fun setupMenuItems() = with(viewBinding) {
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {

                R.id.action_save_image -> {
                    saveImage()
                    true
                }

                else -> false
            }
        }
    }

    private fun loadExtras() = with(intent) {
        redditPost = getParcelableExtra(ImageViewerNavigation.REDDIT_POST_EXTRA)
    }


    private fun saveImage() {
        accessMediaPermissionRequest.request(this) {
        }
    }
}
