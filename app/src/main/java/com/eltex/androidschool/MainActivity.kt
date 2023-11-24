package com.eltex.androidschool

import android.annotation.SuppressLint
import android.app.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.eltex.androidschool.databinding.ActivityMainBinding
import com.eltex.androidschool.model.Post
import com.eltex.androidschool.utils.toast
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var post = Post(
            id = 1L,
            content = "Жизнь — это маленькая кухня, на которой мы готовим блюда под названием «счастье»." +
                    " На этой кухне мы сами себе шеф-повара, и только нам решать, какие ингредиенты мы будем" +
                    " добавлять в наши блюда. Важно помнить, что универсального рецепта нет, поэтому творите," +
                    " пробуйте что-то новое. И, может быть, тогда, в конце, вас будет ждать награда.",
            author = "Поль Бокюз",
            published = "11.22.123 21312:231",
            likedByMe = false,
        )

        bindPost(binding, post)

        binding.menu.setOnClickListener {
            toast(R.id.menu, false)
        }

        binding.favorite.setOnClickListener {
            post = post.copy(likedByMe = !post.likedByMe)

            bindPost(binding, post)
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
    }

}

