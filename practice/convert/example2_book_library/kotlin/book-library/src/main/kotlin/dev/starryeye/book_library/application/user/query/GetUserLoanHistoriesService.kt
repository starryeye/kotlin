package dev.starryeye.book_library.application.user.query

import dev.starryeye.book_library.application.user.query.result.BookHistoryResult
import dev.starryeye.book_library.application.user.query.result.GetUserLoanHistoriesResult
import dev.starryeye.book_library.application.user.query.result.UserLoanHistoryResult
import dev.starryeye.book_library.domain.book.BookRepository
import dev.starryeye.book_library.domain.user.UserRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanStatus
import dev.starryeye.book_library.util.findByIdOrThrow
import org.springframework.stereotype.Service

@Service
class GetUserLoanHistoriesService(
    val userRepository: UserRepository,
    val bookRepository: BookRepository,
) {

    fun getUserLoanHistories(): GetUserLoanHistoriesResult {
        return GetUserLoanHistoriesResult(
            userRepository.findAll()
                .map { user ->
                    UserLoanHistoryResult(
                        user.id!!,
                        user.username,
                        user.loanHistories.map { history ->
                            BookHistoryResult(
                                bookId = history.bookId,
                                bookname = bookRepository.findByIdOrThrow(
                                    history.bookId,
                                    "book is not found, id = ${history.bookId}"
                                ).bookname,
                                isReturned = history.status == UserLoanStatus.RETURNED
                            )

                        }
                    )
                }
        )
    }
}