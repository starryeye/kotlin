package dev.starryeye.book_library.controller.user.request

import dev.starryeye.book_library.application.user.command.command.ChangeUsernameCommand
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ChangeUsernameRequest(
    @field:NotNull
    val id: Long?,
    @field:NotBlank
    val username: String?
)

fun ChangeUsernameRequest.toCommand(): ChangeUsernameCommand {
    return ChangeUsernameCommand(
        id = id!!,
        username = this.username!!,
    )
}