package com.gaitan.dev.user_domain.usecase

data class AllCasesOfUser(
    val getUsersCase: GetUsersCase,
    val getUsersByNameCase: GetUsersByNameCase
)
