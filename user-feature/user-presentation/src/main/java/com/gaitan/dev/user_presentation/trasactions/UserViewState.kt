package com.gaitan.dev.user_presentation.trasactions

import com.gaitan.dev.user_domain.model.User

data class UserViewState(
    val getUsers: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val isGetUserService: Boolean = false,
    val searchText:String = "",
    val isSearching: Boolean = false,

)
