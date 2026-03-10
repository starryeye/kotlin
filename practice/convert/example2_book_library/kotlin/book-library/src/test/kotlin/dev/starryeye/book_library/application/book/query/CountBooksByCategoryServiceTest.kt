package dev.starryeye.book_library.application.book.query

import dev.starryeye.book_library.domain.book.Book
import dev.starryeye.book_library.domain.book.BookCategory
import dev.starryeye.book_library.domain.book.BookRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CountBooksByCategoryServiceTest @Autowired constructor(
    private val service: CountBooksByCategoryService,
    private val bookRepository: BookRepository,
) {

    @AfterEach
    fun tearDown() {
        bookRepository.deleteAllInBatch()
    }

    @DisplayName("카테고리별 책 개수를 조회한다.")
    @Test
    fun countBooksByCategory1() {

        // given
        bookRepository.saveAll(
            listOf(
                Book.fixture("book-A", BookCategory.SCIENCE),
                Book.fixture("book-B", BookCategory.SCIENCE),
                Book.fixture("book-C", BookCategory.COMPUTER),
            )
        )

        // when
        val result = service.countBooksByCategory()

        // then
        assertThat(result.stats).hasSize(2)
        assertThat(result.stats)
            .extracting("category", "count")
            .containsExactlyInAnyOrder(
                tuple(BookCategory.SCIENCE, 2),
                tuple(BookCategory.COMPUTER, 1),
            )
    }

    @DisplayName("저장된 책이 없으면 빈 통계를 반환한다.")
    @Test
    fun countBooksByCategory2() {

        // when
        val result = service.countBooksByCategory()

        // then
        assertThat(result.stats).isEmpty()
    }
}
