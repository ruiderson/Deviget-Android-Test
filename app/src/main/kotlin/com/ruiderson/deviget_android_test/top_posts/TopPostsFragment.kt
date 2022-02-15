package com.ruiderson.deviget_android_test.top_posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.ruiderson.deviget_android_test.R
import com.ruiderson.deviget_android_test.base.extensions.viewModels
import com.ruiderson.deviget_android_test.base.extensions.viewBinding
import com.ruiderson.deviget_android_test.databinding.FragmentTopPostsBinding
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import com.ruiderson.deviget_android_test.top_posts.adapter.RedditPostAdapter
import com.ruiderson.deviget_android_test.top_posts.adapter.RedditPostAdapterEvent
import com.ruiderson.deviget_android_test.top_posts.domain.TopPostsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

internal class TopPostsFragment : Fragment(R.layout.fragment_top_posts),
    KodeinAware
{

    override val kodein: Kodein by kodein()

    private val viewModel: TopPostsViewModel by viewModels()

    private val viewBinding: FragmentTopPostsBinding by viewBinding()

    private val adapter: RedditPostAdapter by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        loadRedditTopPosts()
    }

    private fun setupViews() = with(viewBinding) {
        topEntriesRecyclerView.adapter = adapter
        adapter.setOnAdapterEvent{
            handleRedditPostAdapterEvent(it)
        }
    }

    private fun loadRedditTopPosts() = lifecycleScope.launch {
        viewModel.getRedditTopPosts().collectLatest {
            setupRecyclerView(it)
        }
    }

    private suspend fun setupRecyclerView(redditTopPosts: PagingData<RedditPost>) {
        adapter.submitData(redditTopPosts)
    }

    private fun handleRedditPostAdapterEvent(event: RedditPostAdapterEvent) {
        when(event) {
            is RedditPostAdapterEvent.OnItemClicked -> Unit
            is RedditPostAdapterEvent.OnItemDismissed -> Unit
        }
    }
}
