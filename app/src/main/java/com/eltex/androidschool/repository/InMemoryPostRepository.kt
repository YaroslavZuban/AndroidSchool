package com.eltex.androidschool.repository

import com.eltex.androidschool.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryPostRepository : PostRepository {
    private val state = MutableStateFlow(
        Post(
            id = 1L,
            content = "Жизнь — это маленькая кухня, на которой мы готовим блюда под названием «счастье»." +
                    " На этой кухне мы сами себе шеф-повара, и только нам решать, какие ингредиенты мы будем" +
                    " добавлять в наши блюда. Важно помнить, что универсального рецепта нет, поэтому творите," +
                    " пробуйте что-то новое. И, может быть, тогда, в конце, вас будет ждать награда.",
            author = "Поль Бокюз",
            published = "11.22.123 21312:231",
            likedByMe = false,
            eventByMe = false,
        )
    )

    override fun getPost(): Flow<Post> = state.asStateFlow()

    override fun like() {
        state.update {
            it.copy(likedByMe = !it.likedByMe)
        }
    }

    override fun interest() {
        state.update {
            it.copy(eventByMe = !it.eventByMe)
        }
    }
}