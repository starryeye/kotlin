package dev.starryeye.book_library.application.book.query

import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistoryRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CountLoanedBooksService(
    private val userLoanHistoryRepository: UserLoanHistoryRepository
) {

    fun countLoanedBooks(): Int {
        return userLoanHistoryRepository.countByStatus(UserLoanStatus.LOANED).toInt()
    }
}
