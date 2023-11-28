package com.eltex.androidschool.repository

import com.eltex.androidschool.R
import com.eltex.androidschool.model.Post
import com.eltex.androidschool.utils.toast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryPostRepository : PostRepository {
    private val state = MutableStateFlow(
        List(10000) {
            Post(
                id = it + 1L,
                content = "№$it Жизнь — это маленькая кухня, на которой мы готовим блюда под названием «счастье»." +
                        " На этой кухне мы сами себе шеф-повара, и только нам решать, какие ингредиенты мы будем" +
                        " добавлять в наши блюда. Важно помнить, что универсального рецепта нет, поэтому творите," +
                        " пробуйте что-то новое. И, может быть, тогда, в конце, вас будет ждать награда.",
                author = "$it Поль Бокюз",
                published = "11.22.123 21312:231",
                likedByMe = false,
                eventByMe = false,
            )
        }.reversed()
    )

    override fun getPost(): Flow<List<Post>> = state.asStateFlow()

    override fun likeById(id: Long) {
        state.update { posts ->
            posts.map {
                if (id == it.id) {
                    it.copy(likedByMe = !it.likedByMe)
                } else {
                    it
                }
            }
        }
    }

    override fun interestById(id: Long) {
        state.update { posts ->
            posts.map {
                if (id == it.id) {
                    it.copy(eventByMe = !it.eventByMe)
                } else {
                    it
                }
            }
        }
    }
}