package dev.starryeye.book_library.domain.book

import dev.starryeye.book_library.domain.book.projection.CountBooksByCategoryProjection

interface BookQueryRepository {

    fun countGroupByCategory(): List<CountBooksByCategoryProjection>
}