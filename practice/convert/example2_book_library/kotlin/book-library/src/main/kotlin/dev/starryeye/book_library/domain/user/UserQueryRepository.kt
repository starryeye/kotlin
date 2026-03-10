package dev.starryeye.book_library.domain.user

interface UserQueryRepository {

    fun findAllWithLoanHistoriesAndBooks(): List<User>
}