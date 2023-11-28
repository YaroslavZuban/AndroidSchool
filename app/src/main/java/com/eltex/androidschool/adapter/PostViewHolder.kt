package com.eltex.androidschool.adapter

import androidx.recyclerview.widget.RecyclerView
import com.eltex.androidschool.R
import com.eltex.androidschool.databinding.CardPostBinding
import com.eltex.androidschool.model.Post

class PostViewHolder(
    private val binding: CardPostBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindPost(payload: PostPayload) {
        if (payload.event != null) {
            updateEvent(payload.event)
        }
    }

    fun bindPost(
        post: Post,
    ) {
        binding.author.text = post.author
        binding.published.text = post.published
        binding.content.text = post.content
        updateEvent(post.eventByMe)
    }

    private fun updateEvent(event: Boolean) {
        binding.event.text = if (event) {
            1
        } else {
            0
        }.toString()
    }
}