package dev.starryeye.book_library.application.facade

import dev.starryeye.book_library.application.facade.input.ReturnBookInput
import dev.starryeye.book_library.domain.book.Book
import dev.starryeye.book_library.domain.book.BookRepository
import dev.starryeye.book_library.domain.user.User
import dev.starryeye.book_library.domain.user.UserRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistory
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistoryRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanStatus
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReturnBookFacadeTest @Autowired constructor(
    private val facade: ReturnBookFacade,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository
) {

    @AfterEach
    fun tearDown() {
        userLoanHistoryRepository.deleteAllInBatch()
        bookRepository.deleteAllInBatch()
        userRepository.deleteAllInBatch()
    }

    @DisplayName("userId, bookId 를 전달하면 책을 반납할 수 있다.(loan history DB 데이터 업데이트됨)")
    @Test
    fun returnBook1() {

        // given
        val savedBook = bookRepository.save(Book.fixture("A"))
        val savedUser = userRepository.save(User.fixture("A", 20))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(
            user = savedUser,
            book = savedBook,
        ))
        val command = ReturnBookInput(savedBook.id!!, savedUser.id!!)

        // when
        facade.returnBook(command)

        // then
        val result = userLoanHistoryRepository.findAll()
        assertThat(result).hasSize(1)
        assertThat(result.first().id).isNotNull
        assertThat(result.first().book.id).isEqualTo(savedBook.id)
        assertThat(result.first().status).isEqualTo(UserLoanStatus.RETURNED)
        assertThat(result.first().user.id).isEqualTo(savedUser.id)
    }

    @DisplayName("userId, bookId 를 전달하는데 존재하지 않는 bookId 이면, 예외 발생")
    @Test
    fun returnBook2() {

        // given
        val notExistBookId = 999L
        val savedUser = userRepository.save(User.fixture("A", 20))
        val command = ReturnBookInput(notExistBookId, savedUser.id!!)

        // when
        // then
        assertThatThrownBy { facade.returnBook(command) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("book is not found, id = $notExistBookId")
    }

    @DisplayName("userId, bookId 를 전달하는데 존재하지 않는 userId 이면, 예외 발생")
    @Test
    fun returnBook3() {

        // given
        val savedBook = bookRepository.save(Book.fixture("A"))
        val notExistUserId = 999L
        val command = ReturnBookInput(savedBook.id!!, notExistUserId)

        // when
        // then
        assertThatThrownBy { facade.returnBook(command) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("user is not found, id = $notExistUserId")
    }

    @DisplayName("userId, bookId 를 전달하는데 기존에 빌린 userId 가 아닌 userId 의 사용자가 반납하려하면, 예외 발생")
    @Test
    fun returnBook4() {

        // given
        val savedBook = bookRepository.save(Book.fixture("A"))
        val loanUser = userRepository.save(User.fixture("A", 20))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(
            user = loanUser,
            book = savedBook,
        ))
        val notLoanUser = userRepository.save(User.fixture("B", 20))
        val command = ReturnBookInput(savedBook.id!!, notLoanUser.id!!)

        // when
        // then
        assertThatThrownBy { facade.returnBook(command) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("active loan history is not found, bookId = ${savedBook.id}, userId = ${notLoanUser.id}")
    }

    @DisplayName("userId, bookId 를 전달하는데 이미 반납한 책을 반납하려하면, 예외 발생")
    @Test
    fun returnBook5() {

        // given
        val savedBook = bookRepository.save(Book.fixture("A"))
        val savedUser = userRepository.save(User.fixture("A", 20))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(
            user = savedUser,
            book = savedBook,
            status = UserLoanStatus.RETURNED
        ))
        val command = ReturnBookInput(savedBook.id!!, savedUser.id!!)

        // when
        // then
        assertThatThrownBy { facade.returnBook(command) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("active loan history is not found, bookId = ${savedBook.id}, userId = ${savedUser.id}")
    }
}
