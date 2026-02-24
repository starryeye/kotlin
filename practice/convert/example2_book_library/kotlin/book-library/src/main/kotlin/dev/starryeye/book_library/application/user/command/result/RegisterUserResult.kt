package dev.starryeye.book_library.application.user.command.result

import dev.starryeye.book_library.domain.user.User

data class RegisterUserResult(
    val id: Long,
    val username: String,
    val age: Int?,
) {

    companion object {
        fun of(user: User): RegisterUserResult {
            return RegisterUserResult(
                id = user.id!!, // DB 거쳐서 나온 엔티티에는 id 가 항상 존재한다.
                username = user.username,
                age = user.age
            )
        }
    }
}