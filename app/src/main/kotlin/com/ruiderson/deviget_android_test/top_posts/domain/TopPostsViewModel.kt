package com.ruiderson.deviget_android_test.top_posts.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

internal class TopPostsViewModel(
    override val kodein: Kodein
) : ViewModel(), KodeinAware {

    private val useCase: TopPostsUseCase by kodein.instance()

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
