package dev.starryeye.book_library.controller.book

import dev.starryeye.book_library.application.book.query.CountBooksByCategoryService
import dev.starryeye.book_library.controller.book.response.CountBooksByCategoryResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CountBooksByCategoryController(
    private val countBooksByCategoryService: CountBooksByCategoryService
) {

    @GetMapping("/api/v1/books/stats/category")
    fun countBooksByCategory(): CountBooksByCategoryResponse {
        val result = countBooksByCategoryService.countBooksByCategory()
        return CountBooksByCategoryResponse.of(result)
    }
}
