package dev.starryeye.book_library.controller.user.response

import dev.starryeye.book_library.application.user.command.result.RegisterUserResult

data class UserResponse(
    val id: Long,
    val username: String,
    val age: Int?,
) {

    companion object {
        fun of(userResult: RegisterUserResult): UserResponse {
            return UserResponse(
                id = userResult.id,
                username = userResult.username,
                age = userResult.age,
            )
        }
    }
}