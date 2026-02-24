package dev.starryeye.book_library.application.user.command.command

data class RegisterUserCommand(
    val username: String,
    val age: Int?
) {

    init {
        require(username.isNotBlank()) { "username must not be blank" }
    }
}