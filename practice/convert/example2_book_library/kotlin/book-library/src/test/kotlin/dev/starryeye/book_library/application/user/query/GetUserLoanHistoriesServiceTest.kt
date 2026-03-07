package dev.starryeye.book_library.application.user.query

import dev.starryeye.book_library.domain.book.Book
import dev.starryeye.book_library.domain.book.BookRepository
import dev.starryeye.book_library.domain.user.User
import dev.starryeye.book_library.domain.user.UserRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistory
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistoryRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanStatus
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GetUserLoanHistoriesServiceTest @Autowired constructor(
    private val service: GetUserLoanHistoriesService,
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

    @DisplayName("유저별 대출 이력을 조회한다.")
    @Test
    fun getUserLoanHistories1() {

        // given
        val savedUserA = userRepository.save(User.fixture("A", 20))
        val savedUserB = userRepository.save(User.fixture("B", 30))
        val savedBookA = bookRepository.save(Book.fixture("book-A"))
        val savedBookB = bookRepository.save(Book.fixture("book-B"))

        userLoanHistoryRepository.saveAll(
            listOf(
                UserLoanHistory.fixture(
                    user = savedUserA,
                    book = savedBookA,
                    status = UserLoanStatus.LOANED
                ),
                UserLoanHistory.fixture(
                    user = savedUserA,
                    book = savedBookB,
                    status = UserLoanStatus.RETURNED
                )
            )
        )

        // when
        val result = service.getUserLoanHistories()

        // then
        assertThat(result.histories).hasSize(2)

        val userAHistory = result.histories.first { it.userId == savedUserA.id }
        assertThat(userAHistory.username).isEqualTo("A")
        assertThat(userAHistory.books).hasSize(2)
        assertThat(userAHistory.books)
            .extracting("bookId", "bookname", "isReturned")
            .containsExactlyInAnyOrder(
                tuple(savedBookA.id, "book-A", false),
                tuple(savedBookB.id, "book-B", true),
            )

        val userBHistory = result.histories.first { it.userId == savedUserB.id }
        assertThat(userBHistory.username).isEqualTo("B")
        assertThat(userBHistory.books).isEmpty()
    }
}
