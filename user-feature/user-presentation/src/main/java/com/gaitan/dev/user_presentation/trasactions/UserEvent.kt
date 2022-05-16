package com.gaitan.dev.user_presentation.trasactions

sealed class UserEvent {
    object OnGetUsers : UserEvent()
    data class OnSearchTextChange(val query: String) : UserEvent()
}