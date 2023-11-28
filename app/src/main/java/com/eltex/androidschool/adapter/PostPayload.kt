package com.eltex.androidschool.adapter

data class PostPayload(val event: Boolean? = null) {
    fun isNotEmpty(): Boolean = event != null
}
