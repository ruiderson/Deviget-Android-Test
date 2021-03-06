package com.ruiderson.deviget_android_test.top_posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.ruiderson.deviget_android_test.R
import com.ruiderson.deviget_android_test.base.extensions.viewModels
import com.ruiderson.deviget_android_test.base.extensions.viewBinding
import com.ruiderson.deviget_android_test.databinding.FragmentTopPostsBinding
import com.ruiderson.deviget_android_test.shared.domain.SharedRedditPostViewModel
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import com.ruiderson.deviget_android_test.top_posts.adapter.RedditPostAdapter
import com.ruiderson.deviget_android_test.top_posts.adapter.RedditPostAdapterEvent
import com.ruiderson.deviget_android_test.top_posts.domain.TopPostsViewModel
import kotlinx.coroutines.flow.collect
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

    private val sharedViewModel: SharedRedditPostViewModel by viewModels()

    private val viewBinding: FragmentTopPostsBinding by viewBinding()

    private val adapter: RedditPostAdapter by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        loadRedditTopPosts()
    }

    private fun setupViews() = with(viewBinding) {
        handleRedditPostAdapterStates()
        topEntriesRecyclerView.adapter = adapter
        adapter.setOnAdapterEvent {
            handleRedditPostAdapterEvent(it)
        }

        topEntriesSwipeRefresh.setOnRefreshListener {
            viewModel.shouldRefresh()
            adapter.refresh()
        }

        dismissAllButton.setOnClickListener {
            viewModel.dismissAll()
            sharedViewModel.onDismissAll()
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

    private fun handleRedditPostAdapterStates() = lifecycleScope.launch {
        adapter.loadStateFlow.collect {
            viewBinding.topEntriesSwipeRefresh.isRefreshing = it.refresh is LoadState.Loading
        }
    }

    private fun handleRedditPostAdapterEvent(event: RedditPostAdapterEvent) {
        when(event) {
            is RedditPostAdapterEvent.OnItemClicked -> onItemClicked(event.redditPost)
            is RedditPostAdapterEvent.OnItemDismissed -> onItemDismissed(event.redditPost)
        }
    }

    private fun onItemClicked(redditPost: RedditPost) {
        viewModel.markRedditPostAsRead(redditPost)
        sharedViewModel.onRedditPostClicked(redditPost)
    }

    private fun onItemDismissed(redditPost: RedditPost) {
        viewModel.markRedditPostAsDismissed(redditPost)
        sharedViewModel.onRedditPostDismissed(redditPost)
    }
}
