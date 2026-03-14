package dev.starryeye.book_library.domain.user.loan_history

interface UserLoanHistoryQueryRepository {

    /**
     * fun findByBookBookname(bookname: String): List<UserLoanHistory>
     * fun findByBookBooknameAndStatus(bookname: String, status: UserLoanStatus): List<UserLoanHistory>
     * 위 두함수를 통합함.
     * 분석해보면, bookname 은 두 함수 모두 필수이고 status 는 한군데에서만 필수이다.
     *      따라서, 최종 형태는 아래와 같음
     */
    fun findBy(bookname: String, status: UserLoanStatus? = null): List<UserLoanHistory>

    fun countByStatus(status: UserLoanStatus): Long
}