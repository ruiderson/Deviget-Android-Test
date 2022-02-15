package com.ruiderson.deviget_android_test.top_posts.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ruiderson.deviget_android_test.databinding.ItemRedditPostBinding
import com.ruiderson.deviget_android_test.base.extensions.invisible
import com.ruiderson.deviget_android_test.base.extensions.show
import com.ruiderson.deviget_android_test.shared.models.RedditPost

internal class RedditPostViewHolder(
    private val viewBinding: ItemRedditPostBinding,
    private val onAdapterEvent: (RedditPostAdapterEvent) -> Unit
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(item: RedditPost) = with(viewBinding) {
        redditPostTitle.text = item.title
        if (item.isUnread) isUnreadView.show() else isUnreadView.invisible()

        root.setOnClickListener {
            onAdapterEvent(RedditPostAdapterEvent.OnItemClicked(item))
        }

        //onAdapterEvent(RedditPostAdapterEvent.OnItemDismissed(item))
    }
}
