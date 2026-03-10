package dev.starryeye.book_library.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User, Long>, UserQueryRepository {

    @Query(
        """
        select distinct u
        from User u
        left join fetch u.loanHistories lh
        left join fetch lh.book
        """
    )
    fun findAllWithLoanHistoriesAndBooks(): List<User>

    @Query(
        """
        select distinct u
        from User u
        left join fetch u.loanHistories
        where u.id = :userId
        """
    )
    fun findByIdWithLoanHistories(
        @Param("userId") userId: Long
    ): User?
}
