package com.eltex.androidschool.repository

import com.eltex.androidschool.model.Post
import kotlinx.coroutines.flow.Flow


interface PostRepository {
    fun getPost(): Flow<List<Post>>
    fun likeById(id: Long)
    fun interestById(id: Long)

}