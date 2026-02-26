package dev.starryeye.book_library.controller.book.request

import dev.starryeye.book_library.application.facade.input.ReturnBookInput
import jakarta.validation.constraints.NotNull

data class ReturnBookRequest(
    @field:NotNull
    val bookId: Long?,
    @field:NotNull
    val userId: Long?,
)

fun ReturnBookRequest.toInput(): ReturnBookInput {
    return ReturnBookInput(
        bookId = bookId!!,
        userId = userId!!
    )
}