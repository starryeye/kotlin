package dev.starryeye.book_library.domain.book.projection

import dev.starryeye.book_library.domain.book.BookCategory

data class CountBooksByCategoryProjection(
    val category: BookCategory,
    val count: Long,
)