package dev.starryeye.book_library.application.user.command

import dev.starryeye.book_library.domain.user.UserRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistoryRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanStatus
import dev.starryeye.book_library.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class LoanBookService(
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository
) {

    fun loan(userId: Long, bookId: Long) {

        if (userLoanHistoryRepository.existsByBookIdAndStatus(bookId, UserLoanStatus.LOANED)) {
            throw IllegalStateException("book is already loaned, id = $bookId")
        }

        val user = userRepository.findByIdOrThrow(userId, "user is not found, id = $userId")

        user.loanBook(bookId)
    }
}
