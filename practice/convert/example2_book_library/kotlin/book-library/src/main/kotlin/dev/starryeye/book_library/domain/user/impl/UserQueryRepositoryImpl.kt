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
     * Spring data JPA 단점.
     * 1. Spring data JPA 만을 사용한다면, 복잡한 쿼리에 대해 @Query 를 사용하여 JPQL 을 작성해야하는데..
     *      이는 문자열로 작성되어 문법 실수가 있다면 runtime 에러로 발생한다.
     * 2. 동적 조건의 쿼리 작성이 어렵다.
     *      조건 경우의 수만큼 JpaRepository 메서드를 하나하나 작성해줘야한다.
     *
     * Querydsl 장점
     * Spring data JPA 의 단점을 보완해준다.
     *      1. 동적 조건 쿼리 작성시 하나의 메서드로 깔끔하게 작성이 가능하다.
     *      2. 문자열이 아닌 코드로 작성하여 compile time 에 문법 오류가 감지 된다.
     */

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