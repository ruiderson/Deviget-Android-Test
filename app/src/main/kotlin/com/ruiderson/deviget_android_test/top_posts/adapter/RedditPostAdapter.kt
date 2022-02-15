package com.ruiderson.deviget_android_test.top_posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ruiderson.deviget_android_test.databinding.ItemRedditPostBinding
import com.ruiderson.deviget_android_test.shared.models.RedditPost

internal abstract class RedditPostAdapter : PagingDataAdapter<RedditPost, RedditPostViewHolder>(RedditPostDiffCallBack()) {

    private var onAdapterEvent: (RedditPostAdapterEvent) -> Unit = {}

    fun setOnAdapterEvent(onAdapterEvent: (RedditPostAdapterEvent) -> Unit) {
        this.onAdapterEvent = onAdapterEvent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ) = RedditPostViewHolder(
        ItemRedditPostBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onAdapterEvent
    )

    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
        if (this.itemCount == position + 1) onBindViewHolderFinished()
    }

    abstract fun onBindViewHolderFinished()

    private class RedditPostDiffCallBack : DiffUtil.ItemCallback<RedditPost>() {
        override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
            return oldItem == newItem
        }
    }
}
