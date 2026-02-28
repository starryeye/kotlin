package dev.starryeye.book_library.controller.book

import dev.starryeye.book_library.application.book.command.RegisterBookService
import dev.starryeye.book_library.controller.book.request.RegisterBookRequest
import dev.starryeye.book_library.controller.book.request.toCommand
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterBookController(
    private val service: RegisterBookService,
) {

    @PostMapping("/api/v1/books/new")
    fun bookRegister(
        @Valid @RequestBody request: RegisterBookRequest,
    ) {
        service.register(request.toCommand())
    }
}