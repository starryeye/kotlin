package dev.starryeye.book_library.domain.user

import com.querydsl.jpa.impl.JPAQueryFactory

class UserQueryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : UserQueryRepository {
}