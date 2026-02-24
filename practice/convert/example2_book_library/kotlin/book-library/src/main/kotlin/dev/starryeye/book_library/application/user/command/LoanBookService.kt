package dev.starryeye.book_library.application.user.command

import dev.starryeye.book_library.domain.user.UserRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class LoanBookService(
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository
) {
}