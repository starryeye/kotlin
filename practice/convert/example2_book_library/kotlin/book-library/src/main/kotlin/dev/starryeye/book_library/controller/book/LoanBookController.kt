package dev.starryeye.book_library.controller.book

import dev.starryeye.book_library.application.facade.LoanBookFacade
import dev.starryeye.book_library.controller.book.request.LoanBookRequest
import dev.starryeye.book_library.controller.book.request.toInput
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoanBookController(
    private val facade: LoanBookFacade,
) {

    @PostMapping("/api/v1/books/loan")
    fun loanBook(
        @Valid @RequestBody request: LoanBookRequest,
    ) {
        facade.loanBook(request.toInput())
    }
}