package dev.starryeye.book_library.controller.book.request

import dev.starryeye.book_library.application.book.command.command.RegisterBookCommand
import dev.starryeye.book_library.domain.book.BookCategory
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class RegisterBookRequest(
    @field:NotBlank
    val bookname: String?,
    @field:NotNull
    val category: BookCategory?,
)

fun RegisterBookRequest.toCommand(): RegisterBookCommand {
    return RegisterBookCommand(
        bookname = this.bookname!!,
        category = this.category!!,
    )
}