package dev.starryeye.book_library.domain.user.loan_history.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.starryeye.book_library.domain.user.loan_history.QUserLoanHistory.userLoanHistory
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistory
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistoryQueryRepository
import dev.starryeye.book_library.domain.user.loan_history.UserLoanStatus

class UserLoanHistoryQueryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : UserLoanHistoryQueryRepository {

    /**
     * 현재 해당 함수를 application 내에서 사용하고 있지 않지만,
     * UserLoanHistory 엔티티만 사용하지 않고 Book 엔티티도 함께 사용해야한다면..
     * fetchJoin() 코드를 추가해야한다.
     */

    override fun findBy(bookname: String, status: UserLoanStatus?): List<UserLoanHistory> {
        return jpaQueryFactory
            .selectFrom(userLoanHistory)
            .where(
                userLoanHistory.book.bookname.eq(bookname),
                // status 매개변수가 null 로 들어오면, status?.let { userLoanHistory.status.eq(status) } 전체가 null 이되고, where() 에서 null 은 무시된다.
                status?.let { userLoanHistory.status.eq(status) },
            )
            .fetch()
    }

    override fun countByStatus(status: UserLoanStatus): Long {
        return jpaQueryFactory
            .select(userLoanHistory.id.count())
            .from(userLoanHistory)
            .where(
                userLoanHistory.status.eq(status)
            )
            .fetchOne() ?: 0L // count 쿼리는 리턴 값이 보장되지만 보통 엘비스 활용하여 안전하게 작성한다.
    }
}