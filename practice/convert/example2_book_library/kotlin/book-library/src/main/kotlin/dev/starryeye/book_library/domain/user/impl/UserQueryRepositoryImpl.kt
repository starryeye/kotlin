package dev.starryeye.book_library.domain.user.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.starryeye.book_library.domain.book.QBook
import dev.starryeye.book_library.domain.user.QUser
import dev.starryeye.book_library.domain.user.User
import dev.starryeye.book_library.domain.user.UserQueryRepository
import dev.starryeye.book_library.domain.user.loan_history.QUserLoanHistory

class UserQueryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : UserQueryRepository {

    /**
     * 참고.
     * 현재 JPA 기준 User, UserLoanHistory, Book 엔티티간에 연관관계(@ManyToOne 과 같은..)가 설정되어있어서
     * join 후 on 절(on() 메서드)은 필요 없음.
     *
     * 하지만, JPA 레벨에서 연관관계가 없는데 쿼리상 join 이 필요한 경우에는 on 절(on() 메서드)이 필요하다.
     */

    override fun findAllWithLoanHistoriesAndBooks(): List<User> {
        return jpaQueryFactory
            .selectFrom(QUser.user)
            .distinct()
            .leftJoin(QUser.user.loanHistories, QUserLoanHistory.userLoanHistory).fetchJoin()
            .leftJoin(QUserLoanHistory.userLoanHistory.book, QBook.book).fetchJoin()
            .fetch()
    }
}