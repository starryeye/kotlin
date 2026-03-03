package dev.starryeye.book_library.application.facade

import dev.starryeye.book_library.application.facade.input.LoanBookInput
import dev.starryeye.book_library.domain.book.Book
import dev.starryeye.book_library.domain.book.BookRepository
import dev.starryeye.book_library.domain.user.User
import dev.starryeye.book_library.domain.user.UserRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LoanBookFacadeTest @Autowired constructor(
    private val facade: LoanBookFacade,
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @AfterEach
    fun tearDown() {
        userLoanHistoryRepository.deleteAllInBatch()
        bookRepository.deleteAllInBatch()
        userRepository.deleteAllInBatch()
    }

    @DisplayName("userId, bookId 를 전달하면 책을 빌릴 수 있다.(loan history DB 데이터 저장됨)")
    @Test
    fun loanBook1() {

        // given
        val savedBook = bookRepository.save(Book("A"))
        val savedUser = userRepository.save(User("A", 20))
        val command = LoanBookInput(savedBook.id!!, savedUser.id!!)

        // when
        facade.loanBook(command)

        // then
        val result = userLoanHistoryRepository.findAll()
        assertThat(result).hasSize(1)
        assertThat(result.first().id).isNotNull
        assertThat(result.first().bookId).isEqualTo(savedBook.id)
        assertThat(result.first().isReturn).isFalse
        assertThat(result.first().user.id).isEqualTo(savedUser.id)
    }

    @DisplayName("userId, bookId 를 전달하는데 존재하지 않는 bookId 이면, 예외 발생")
    @Test
    fun loanBook2() {

        // given
        val notExistBookId = 999L
        val savedUser = userRepository.save(User("A", 20))
        val command = LoanBookInput(notExistBookId, savedUser.id!!)

        // when
        // then
        assertThatThrownBy { facade.loanBook(command) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("book is not found, id = $notExistBookId")
    }

    @DisplayName("userId, bookId 를 전달하는데 존재하지 않는 userId 이면, 예외 발생")
    @Test
    fun loanBook3() {

        // given
        val savedBook = bookRepository.save(Book("A"))
        val notExistUserId = 999L
        val command = LoanBookInput(savedBook.id!!, notExistUserId)

        // when
        // then
        assertThatThrownBy { facade.loanBook(command) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("user is not found, id = $notExistUserId")
    }

    @DisplayName("userId, bookId 를 전달하는데 이미 빌린 책이면 예외 발생")
    @Test
    fun loanBook4() {

        // given
        val savedBook = bookRepository.save(Book("A"))
        val savedUser1 = userRepository.save(User("A", 20))
        val beforeCommand = LoanBookInput(savedBook.id!!, savedUser1.id!!)
        facade.loanBook(beforeCommand)
        val savedUser2 = userRepository.save(User("B", 20))
        val afterCommand = LoanBookInput(savedBook.id!!, savedUser2.id!!)

        // when
        // then
        assertThatThrownBy { facade.loanBook(afterCommand) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("book is already loaned, id = ${savedBook.id}")
    }
}