package dev.starryeye.book_library.controller.book

import dev.starryeye.book_library.application.book.query.CountLoanedBooksService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CountLoanedBooksController(
    private val countLoanedBooksService: CountLoanedBooksService
) {

    @GetMapping("/api/v1/books/loan-count")
    fun countLoanedBooks(): Int = countLoanedBooksService.countLoanedBooks()
}