package dev.starryeye.book_library.application.book.query

import dev.starryeye.book_library.domain.book.Book
import dev.starryeye.book_library.domain.book.BookRepository
import dev.starryeye.book_library.domain.user.User
import dev.starryeye.book_library.domain.user.UserRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistory
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistoryRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CountLoanedBooksServiceTest @Autowired constructor(
    private val service: CountLoanedBooksService,
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

    @DisplayName("대출 상태가 LOANED 인 데이터 수를 조회한다.")
    @Test
    fun countLoanedBooks1() {

        // given
        val savedUser = userRepository.save(User.fixture("A", 20))
        val savedBookA = bookRepository.save(Book.fixture("book-A"))
        val savedBookB = bookRepository.save(Book.fixture("book-B"))
        val savedBookC = bookRepository.save(Book.fixture("book-C"))

        userLoanHistoryRepository.saveAll(
            listOf(
                UserLoanHistory.fixture(
                    user = savedUser,
                    book = savedBookA,
                    status = UserLoanStatus.LOANED
                ),
                UserLoanHistory.fixture(
                    user = savedUser,
                    book = savedBookB,
                    status = UserLoanStatus.RETURNED
                ),
                UserLoanHistory.fixture(
                    user = savedUser,
                    book = savedBookC,
                    status = UserLoanStatus.LOANED
                ),
            )
        )

        // when
        val result = service.countLoanedBooks()

        // then
        assertThat(result).isEqualTo(2)
    }

    @DisplayName("대출 이력이 없으면 0 을 반환한다.")
    @Test
    fun countLoanedBooks2() {

        // when
        val result = service.countLoanedBooks()

        // then
        assertThat(result).isZero()
    }
}
