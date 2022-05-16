package com.gaitan.dev.post_presentation.transactions

sealed class PostEvent {
   data class OnPostsByUser(val userId: Int) : PostEvent()
}