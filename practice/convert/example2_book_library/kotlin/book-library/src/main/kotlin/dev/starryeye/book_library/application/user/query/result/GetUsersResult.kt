package dev.starryeye.book_library.application.user.query.result

import dev.starryeye.book_library.domain.user.User

data class GetUsersResult(
    val users: List<GetUserResult>,
) {

    companion object {
        fun of(users: List<User>): GetUsersResult {
            return GetUsersResult(
                users.map { user ->
                    GetUserResult(
                        id = user.id!!, // DB 거쳐서 나온 엔티티에는 id 가 항상 존재한다.
                        username = user.username,
                        age = user.age
                    )
                }
            )
        }
    }
}