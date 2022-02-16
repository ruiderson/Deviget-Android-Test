package com.ruiderson.deviget_android_test.shared.domain

import com.ruiderson.deviget_android_test.shared.models.RedditPost

internal sealed class SharedRedditPostState {

    data class OnShowPostDetails(val redditPost: RedditPost) : SharedRedditPostState()

    object OnHidePostDetails : SharedRedditPostState()
}
