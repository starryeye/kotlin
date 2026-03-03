package dev.starryeye.book_library.application.facade

import dev.starryeye.book_library.domain.book.BookRepository
import dev.starryeye.book_library.domain.user.UserRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistoryRepository
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

    @DisplayName("userId, bookId 를 전달하면 책을 빌릴 수 있다.")
    @Test
    fun loanBook() {

        // given
        // when
        // then
    }
}