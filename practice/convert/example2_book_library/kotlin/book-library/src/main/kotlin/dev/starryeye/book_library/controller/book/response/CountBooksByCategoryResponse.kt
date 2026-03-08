package dev.starryeye.book_library.controller.book.response

import dev.starryeye.book_library.application.book.query.result.CountBooksByCategoryResult
import dev.starryeye.book_library.domain.book.BookCategory

data class CountBooksByCategoryResponse(
    val stats: List<CountBookByCategoryResponse>
) {
    companion object {
        fun of(result: CountBooksByCategoryResult): CountBooksByCategoryResponse {
            return CountBooksByCategoryResponse(
                result.stats.map { bookByCategoryResult ->
                    CountBookByCategoryResponse(
                        category = bookByCategoryResult.category,
                        count = bookByCategoryResult.count,
                    )
                }
            )
        }
    }
}

data class CountBookByCategoryResponse(
    val category: BookCategory,
    val count: Int,
)