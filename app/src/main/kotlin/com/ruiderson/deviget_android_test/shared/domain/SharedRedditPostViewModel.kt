package com.ruiderson.deviget_android_test.shared.domain

import androidx.lifecycle.ViewModel
import com.ruiderson.deviget_android_test.shared.models.RedditPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class SharedRedditPostViewModel : ViewModel() {

    private val _redditPostState = MutableStateFlow<SharedRedditPostState>(SharedRedditPostState.OnHidePostDetails)
    val redditPostState: StateFlow<SharedRedditPostState> = _redditPostState

    private var currentPostId: String? = null

    fun onRedditPostClicked(redditPost: RedditPost) {
        currentPostId = redditPost.id
        _redditPostState.value = SharedRedditPostState.OnShowPostDetails(redditPost)
    }

    fun onRedditPostDismissed(redditPost: RedditPost) {
        if (redditPost.id == currentPostId) {
            onDismissAll()
        }
    }

    fun onDismissAll() {
        currentPostId = null
        _redditPostState.value = SharedRedditPostState.OnHidePostDetails
    }
}
