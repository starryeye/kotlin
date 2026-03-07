package dev.starryeye.book_library.application.user.query.result

data class GetUserLoanHistoriesResult(
    val histories: List<UserLoanHistoryResult>
)

data class UserLoanHistoryResult(
    val userId: Long,
    val username: String,
    val books: List<BookHistoryResult>
)

data class BookHistoryResult(
    val bookId: Long,
    val bookname: String,
    val isReturned: Boolean
)