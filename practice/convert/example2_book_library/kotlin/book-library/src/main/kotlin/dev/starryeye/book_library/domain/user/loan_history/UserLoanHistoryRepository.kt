package dev.starryeye.book_library.domain.user.loan_history

import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {

    fun existsByBookIdAndIsReturn(bookId: Long, isReturn: UserLoanStatus): Boolean
}
