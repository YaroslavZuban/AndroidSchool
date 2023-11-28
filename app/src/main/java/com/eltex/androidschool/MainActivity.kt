package com.eltex.androidschool

import android.annotation.SuppressLint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

import com.eltex.androidschool.databinding.ActivityMainBinding
import com.eltex.androidschool.model.Post
import com.eltex.androidschool.repository.InMemoryPostRepository
import com.eltex.androidschool.utils.toast
import com.eltex.androidschool.viewmodel.PostViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<PostViewModel> {
            viewModelFactory {
                initializer {
                    PostViewModel(InMemoryPostRepository())
                }
            }
        }

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel.state.flowWithLifecycle(lifecycle).onEach { bindPost(binding, it.post) }
            .launchIn(lifecycleScope)


        binding.menu.setOnClickListener {
            toast(R.id.menu, false)
        }

        binding.favorite.setOnClickListener {
            viewModel.like()
        }

        binding.event.setOnClickListener {
            viewModel.interest()
        }
    }

    private fun bindPost(
        binding: ActivityMainBinding,
        post: Post,
    ) {
        binding.author.text = post.author
        binding.published.text = post.published
        binding.content.text = post.content

        binding.favorite.setIconResource(
            if (post.likedByMe) {
                R.drawable.baseline_favorite_24
            } else {
                R.drawable.baseline_favorite_border_24
            }
        )

        binding.favorite.text = if (post.likedByMe) {
            1
        } else {
            0
        }.toString()

        binding.event.text = if (post.eventByMe) {
            1
        } else {
            0
        }.toString()
    }

}

