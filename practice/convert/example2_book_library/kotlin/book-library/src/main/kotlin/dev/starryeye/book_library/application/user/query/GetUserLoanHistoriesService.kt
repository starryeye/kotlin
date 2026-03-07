package dev.starryeye.book_library.application.user.query

import dev.starryeye.book_library.application.user.query.result.BookHistoryResult
import dev.starryeye.book_library.application.user.query.result.GetUserLoanHistoriesResult
import dev.starryeye.book_library.application.user.query.result.UserLoanHistoryResult
import dev.starryeye.book_library.domain.user.UserRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetUserLoanHistoriesService(
    val userRepository: UserRepository,
) {

    fun getUserLoanHistories(): GetUserLoanHistoriesResult {
        return GetUserLoanHistoriesResult(
            userRepository.findAllWithLoanHistoriesAndBooks()
                .map { user ->
                    UserLoanHistoryResult(
                        user.id!!,
                        user.username,
                        user.loanHistories.map { history ->
                            BookHistoryResult(
                                bookId = history.book.id!!,
                                bookname = history.book.bookname,
                                isReturned = history.status == UserLoanStatus.RETURNED
                            )

                        }
                    )
                }
        )
    }
}
