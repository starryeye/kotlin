package dev.starryeye.book_library.controller.user.response

import dev.starryeye.book_library.application.user.query.result.GetUserLoanHistoriesResult

data class UserLoanHistoriesResponse(
    val histories: List<UserLoanHistoryResponse>
) {

    companion object {
        fun of(result: GetUserLoanHistoriesResult): UserLoanHistoriesResponse {
            return UserLoanHistoriesResponse(
                result.histories.map { historyResult ->
                    UserLoanHistoryResponse(
                        historyResult.userId,
                        historyResult.username,
                        historyResult.books.map { bookHistoryResult ->
                            BookHistoryResponse(
                                bookId = bookHistoryResult.bookId,
                                bookname = bookHistoryResult.bookname,
                                isReturned = bookHistoryResult.isReturned
                            )
                        }
                    )
                }
            )
        }
    }
}

data class UserLoanHistoryResponse(
    val userId: Long,
    val username: String,
    val books: List<BookHistoryResponse>
)

data class BookHistoryResponse(
    val bookId: Long,
    val bookname: String,
    val isReturned: Boolean
)
