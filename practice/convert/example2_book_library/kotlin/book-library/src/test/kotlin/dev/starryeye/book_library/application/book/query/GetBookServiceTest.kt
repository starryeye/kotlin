package dev.starryeye.book_library.application.book.query

import dev.starryeye.book_library.domain.book.Book
import dev.starryeye.book_library.domain.book.BookRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GetBookServiceTest @Autowired constructor(
    private val service: GetBookService,
    private val bookRepository: BookRepository,
) {

    @AfterEach
    fun tearDown() {
        bookRepository.deleteAllInBatch()
    }

    @DisplayName("bookId 를 전달하여 DB 에 해당 데이터가 존재하는지 확인한다.")
    @Test
    fun existsBy1() {

        // given
        val saved = bookRepository.save(Book.fixture("A"))
        val bookId: Long = saved.id!!

        // when
        // then
        val result = service.existsBy(bookId)
        assertThat(result).isTrue
    }

    @DisplayName("bookId 를 전달하여 DB 에 해당 데이터가 존재하는지 확인한다. 없으면..")
    @Test
    fun existsBy2() {

        // given
        val notExistId = 9999L

        // when
        // then
        val result = service.existsBy(notExistId)
        assertThat(result).isFalse
    }
}