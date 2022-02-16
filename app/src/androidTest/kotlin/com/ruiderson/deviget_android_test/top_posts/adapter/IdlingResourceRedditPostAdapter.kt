package com.ruiderson.deviget_android_test.top_posts.adapter

import androidx.test.espresso.idling.CountingIdlingResource

internal class IdlingResourceRedditPostAdapter(
    private val idlingResource: CountingIdlingResource
) : RedditPostAdapter() {

    private var isIdleIncremented = true

    init {
        idlingResource.increment()
    }

    override fun onItemBind(position: Int) {
        if(isIdleIncremented) {
            idlingResource.decrement()
            isIdleIncremented = false
        }
    }
}
