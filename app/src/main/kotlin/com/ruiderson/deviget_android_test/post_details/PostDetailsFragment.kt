package com.ruiderson.deviget_android_test.post_details

import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ruiderson.deviget_android_test.R
import com.ruiderson.deviget_android_test.base.extensions.viewModels
import com.ruiderson.deviget_android_test.base.extensions.viewBinding
import com.ruiderson.deviget_android_test.base.extensions.show
import com.ruiderson.deviget_android_test.base.extensions.hide
import com.ruiderson.deviget_android_test.base.extensions.uiLifecycleScope
import com.ruiderson.deviget_android_test.databinding.FragmentPostDetailsBinding
import com.ruiderson.deviget_android_test.image_viewer.domain.ImageViewerNavigation
import com.ruiderson.deviget_android_test.shared.domain.SharedRedditPostState
import com.ruiderson.deviget_android_test.shared.domain.SharedRedditPostViewModel
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import kotlinx.coroutines.flow.collect
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

internal class PostDetailsFragment : Fragment(R.layout.fragment_post_details),
    KodeinAware
{

    override val kodein: Kodein by kodein()

    private val imageViewerNavigation: ImageViewerNavigation by instance()

    private val sharedViewModel: SharedRedditPostViewModel by viewModels()

    private val viewBinding: FragmentPostDetailsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() = uiLifecycleScope {
        sharedViewModel.redditPostState.collect {
            onStateChange(it)
        }
    }

    private fun onStateChange(state: SharedRedditPostState) = when(state) {
        is SharedRedditPostState.OnShowPostDetails -> onShowPostDetails(state.redditPost)
        is SharedRedditPostState.OnHidePostDetails -> onHidePostDetails()
    }

    private fun onShowPostDetails(redditPost: RedditPost) = with(viewBinding) {
        with(postDetailsAuthor) {
            text = redditPost.author
            show()
        }
        with(postDetailsTitle) {
            text = redditPost.title
            show()
        }

        if(redditPost.thumbnail != null && URLUtil.isValidUrl(redditPost.thumbnail)) {
            with(postDetailsImageView) {
                Glide
                    .with(this.context)
                    .load(redditPost.thumbnail)
                    .centerCrop()
                    .into(this)
                show()
                setOnClickListener {
                    imageViewerNavigation.navigateToImageViewer(this.context, redditPost)
                }
            }
        } else {
            postDetailsImageView.hide()
            postDetailsImageView.setOnClickListener { }
        }
    }

    private fun onHidePostDetails() = with(viewBinding) {
        postDetailsAuthor.hide()
        postDetailsTitle.hide()
        postDetailsImageView.hide()
    }
}
