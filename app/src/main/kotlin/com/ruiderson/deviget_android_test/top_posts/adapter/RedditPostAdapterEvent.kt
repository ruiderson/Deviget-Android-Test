package com.ruiderson.deviget_android_test.top_posts.adapter

import com.ruiderson.deviget_android_test.shared.models.RedditPost

internal sealed class RedditPostAdapterEvent {

    data class OnItemClicked(val redditPost: RedditPost) : RedditPostAdapterEvent()

    data class OnItemDismissed(val redditPost: RedditPost) : RedditPostAdapterEvent()
}
