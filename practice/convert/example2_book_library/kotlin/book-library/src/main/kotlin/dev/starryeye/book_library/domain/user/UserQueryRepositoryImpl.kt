package dev.starryeye.book_library.domain.user

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.starryeye.book_library.domain.user.QUser.user
import dev.starryeye.book_library.domain.user.loan_history.QUserLoanHistory.userLoanHistory

class UserQueryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : UserQueryRepository {

    override fun findAllWithLoanHistoriesAndBooks(): List<User> {
        return jpaQueryFactory.select(user).distinct()
            .from(user)
            .leftJoin(user.loanHistories).on(user.id.eq(userLoanHistory.user.id))
            .fetchJoin()
            .fetch()
    }
}