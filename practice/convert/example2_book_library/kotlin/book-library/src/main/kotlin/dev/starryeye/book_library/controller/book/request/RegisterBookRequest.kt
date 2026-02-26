package dev.starryeye.book_library.controller.book.request

import dev.starryeye.book_library.application.book.command.command.RegisterBookCommand
import jakarta.validation.constraints.NotBlank

data class RegisterBookRequest(
    @field:NotBlank
    val bookname: String?,
)

fun RegisterBookRequest.toCommand(): RegisterBookCommand {
    return RegisterBookCommand(
        bookname = this.bookname!!
    )
}