package dev.starryeye.book_library.application.book.query.result

import dev.starryeye.book_library.domain.book.BookCategory

data class CountBooksByCategoryResult(
    val stats: List<CountBookByCategoryResult>
)

data class CountBookByCategoryResult(
    val category: BookCategory,
    val count: Int
)
