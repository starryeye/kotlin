package dev.starryeye.book_library.controller.book.request

import dev.starryeye.book_library.application.facade.input.LoanBookInput
import jakarta.validation.constraints.NotNull

data class LoanBookRequest(
    @field:NotNull(message = "bookId must not be null")
    val bookId: Long?,
    @field:NotNull(message = "userId must not be null")
    val userId: Long?,
)

fun LoanBookRequest.toInput(): LoanBookInput =
    LoanBookInput(
        bookId = this.bookId!!,
        userId = this.userId!!,
    )