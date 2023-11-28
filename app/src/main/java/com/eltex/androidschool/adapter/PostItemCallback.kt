package com.eltex.androidschool.adapter

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.eltex.androidschool.model.Post

class PostItemCallback : ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Post, newItem: Post): Any? =
        PostPayload(
            event = newItem.eventByMe.takeIf { it != oldItem.eventByMe },
        ).takeIf { it.isNotEmpty() }
}