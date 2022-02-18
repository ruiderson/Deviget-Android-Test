package com.ruiderson.deviget_android_test.top_posts.adapter

import android.webkit.URLUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruiderson.deviget_android_test.R
import com.ruiderson.deviget_android_test.base.extensions.hide
import com.ruiderson.deviget_android_test.databinding.ItemRedditPostBinding
import com.ruiderson.deviget_android_test.base.extensions.invisible
import com.ruiderson.deviget_android_test.base.extensions.show
import com.ruiderson.deviget_android_test.shared.models.RedditPost

internal class RedditPostViewHolder(
    private val viewBinding: ItemRedditPostBinding,
    private val onAdapterEvent: (RedditPostAdapterEvent) -> Unit
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(item: RedditPost) = with(viewBinding) {
        if (item.isUnread) isUnreadView.show() else isUnreadView.invisible()
        redditPostAuthor.text = item.author
        redditPostEntryDate.text = item.entry_date
        redditPostTitle.text = item.title

        with(redditPostComments) {
            val comments = "${item.num_comments} ${this.context.getString(R.string.comments)}"
            text = comments
        }

        if(item.thumbnail != null && URLUtil.isValidUrl(item.thumbnail)) {
            with(redditPostImageView) {
                Glide
                    .with(this.context)
                    .load(item.thumbnail)
                    .centerCrop()
                    .into(this)
                redditPostImageView.show()
            }
        } else {
            redditPostImageView.hide()
        }

        redditPostDismissButton.setOnClickListener {
            onAdapterEvent(RedditPostAdapterEvent.OnItemDismissed(item))
        }

        root.setOnClickListener {
            isUnreadView.invisible()
            onAdapterEvent(RedditPostAdapterEvent.OnItemClicked(item))
        }
    }
}
