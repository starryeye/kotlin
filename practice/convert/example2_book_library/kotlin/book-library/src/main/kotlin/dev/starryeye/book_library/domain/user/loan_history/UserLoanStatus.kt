package dev.starryeye.book_library.domain.user.loan_history

enum class UserLoanStatus(
    val description: String,
) {

    RETURNED("반납 상태"),
    LOANED("대출 중"),
}