package dev.starryeye.book_library.controller.user.response

import dev.starryeye.book_library.application.user.query.result.GetUsersResult

data class UsersResponse(
    val users: List<UserResponse>,
) {

    companion object {
        fun of(usersResult: GetUsersResult): UsersResponse {
            return UsersResponse(
                usersResult.users.map { userResult -> UserResponse(
                    userResult.id,
                    userResult.username,
                    userResult.age
                ) }
            )
        }
    }
}