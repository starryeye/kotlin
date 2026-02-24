package dev.starryeye.book_library.application.user.command.command

data class ChangeUsernameCommand(
    val id: Long,
    val username: String
) {

    init {
        require(username.isNotBlank()) { "username must not be blank" }
    }
}