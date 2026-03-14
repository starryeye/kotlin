package dev.starryeye.book_library.domain.book.impl

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.starryeye.book_library.domain.book.BookQueryRepository
import dev.starryeye.book_library.domain.book.QBook.book
import dev.starryeye.book_library.domain.book.projection.CountBooksByCategoryProjection

class BookQueryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : BookQueryRepository {

    override fun countGroupByCategory(): List<CountBooksByCategoryProjection> {
        return jpaQueryFactory
            .select(
                Projections.constructor(
                    CountBooksByCategoryProjection::class.java,
                    book.category,
                    book.id.count()
                )
            )
            .from(book)
            .groupBy(book.category)
            .fetch()
    }
}