package dev.starryeye.book_library.controller.user.request

import dev.starryeye.book_library.application.user.command.command.RegisterUserCommand
import jakarta.validation.constraints.NotBlank

class RegisterUserRequest(
    @field:NotBlank
    val username: String?,
    val age: Int?,
)

fun RegisterUserRequest.toCommand(): RegisterUserCommand {
    return RegisterUserCommand(
        username = this.username!!,
        age = this.age
    )
}