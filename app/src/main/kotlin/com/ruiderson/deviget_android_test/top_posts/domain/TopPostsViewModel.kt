package com.ruiderson.deviget_android_test.top_posts.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import kotlinx.coroutines.launch

internal class TopPostsViewModel(
    private val useCase: TopPostsUseCase
) : ViewModel() {

    fun getRedditTopPosts() = useCase.getRedditTopPosts()

    fun shouldRefresh() = viewModelScope.launch {
        useCase.shouldRefresh()
    }

    fun markRedditPostAsRead(redditPost: RedditPost) = viewModelScope.launch {
        useCase.markRedditPostAsRead(redditPost)
    }

    fun markRedditPostAsDismissed(redditPost: RedditPost) = viewModelScope.launch {
        useCase.markRedditPostAsDismissed(redditPost)
    }

    fun dismissAll() = viewModelScope.launch {
        useCase.dismissAll()
    }
}
