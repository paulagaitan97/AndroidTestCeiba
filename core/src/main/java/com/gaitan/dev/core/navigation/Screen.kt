package com.gaitan.dev.core.navigation

sealed class Screen(val route: String){
    object AllUserScreen: Screen("all_users_screen")
    object AllPostByUserScreen: Screen("post_screen")
}
