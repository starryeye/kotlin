package dev.starryeye.book_library.controller.book

import dev.starryeye.book_library.application.facade.ReturnBookFacade
import dev.starryeye.book_library.controller.book.request.ReturnBookRequest
import dev.starryeye.book_library.controller.book.request.toInput
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReturnBookController(
    private val facade: ReturnBookFacade
) {

    @PostMapping("/api/v1/books/return")
    fun returnBook(
        @Valid @RequestBody request: ReturnBookRequest,
    ) {
        facade.returnBook(request.toInput())
    }
}