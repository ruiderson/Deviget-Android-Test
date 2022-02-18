package com.ruiderson.deviget_android_test.image_viewer

import android.graphics.Bitmap
import android.graphics.Canvas
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
import com.ruiderson.deviget_android_test.base.utils.ToastUtil
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
                    accessMediaPermissionRequest.request(this@ImageViewerActivity) {
                        saveImage()
                    }

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
        val bitmap = createBitmap()
        val result = viewModel.saveBitmap(bitmap)
        showSaveImageResult(result)
    }

    private fun createBitmap(): Bitmap = with(viewBinding.imageViewer) {
        val bitmap = Bitmap.createBitmap(width, height, BITMAP_CONFIG)
        draw(Canvas(bitmap))
        return bitmap
    }

    private fun showSaveImageResult(result: Boolean) {
        ToastUtil.show(this,
            if (result) R.string.image_saved_success else R.string.image_saved_fail
        )
    }

    companion object {
        private val BITMAP_CONFIG = Bitmap.Config.ARGB_8888
    }
}
