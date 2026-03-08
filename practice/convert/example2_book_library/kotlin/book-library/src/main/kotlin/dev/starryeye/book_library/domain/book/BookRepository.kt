package dev.starryeye.book_library.domain.book

import dev.starryeye.book_library.domain.book.projection.CountBooksByCategoryProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookRepository : JpaRepository<Book, Long> {

    @Query(
        "select new dev.starryeye.book_library.domain.book.projection.CountBooksByCategoryProjection(b.category, count(b)) " +
            "from Book b group by b.category"
    )
    fun countGroupByCategory(): List<CountBooksByCategoryProjection>
}
