package dev.starryeye.book_library.application.book.command

import dev.starryeye.book_library.application.book.command.command.RegisterBookCommand
import dev.starryeye.book_library.domain.book.BookRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RegisterBookServiceTest @Autowired constructor(
    private val service: RegisterBookService,
    private val bookRepository: BookRepository,
) {

    @AfterEach
    fun tearDown() {
        bookRepository.deleteAllInBatch()
    }

    @DisplayName("bookname 으로 책을 등록한다.")
    @Test
    fun registerBook() {

        // given
        val command = RegisterBookCommand("A")

        // when
        service.register(command)

        // then
        val result = bookRepository.findAll()
        assertThat(result).hasSize(1)
        assertThat(result.first().id).isNotNull
        assertThat(result.first().bookname).isEqualTo(command.bookname)
    }

}