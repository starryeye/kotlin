package dev.starryeye.book_library.domain.user.loan_history

import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long>, UserLoanHistoryQueryRepository {

    /**
     * 아래 쿼리는
     * UserLoanHistories table 에 book_id FK 가 존재하여 join 되지 않는다.
     */
    fun existsByBookIdAndStatus(bookId: Long, status: UserLoanStatus): Boolean

    /**
     * 아래 쿼리는 정적이므로 보통 JpaRepository 에 그냥 두는게 좋지만, querydsl 예시로 쓰기 위해 querydsl 로 대채한다.
     */
//    fun countByStatus(status: UserLoanStatus): Long

    /**
     * 아래 두 쿼리는 동적 조건의 쿼리를 querydsl 로 대체하여 하나의 쿼리로 제공하는 예시를 보여주기 위함임. (application 내에서 사용되지 않음)
     * BookBookname 은 UserLoanHistory 엔티티에서 Book 엔티티 연관관계내의 bookname 을 뜻하는 메서드이름 방식 문법임.
     */
    // querydsl 로 대체
//    fun findByBookBookname(bookname: String): List<UserLoanHistory>
//
//    fun findByBookBooknameAndStatus(bookname: String, status: UserLoanStatus): List<UserLoanHistory>
}
