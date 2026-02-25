package dev.starryeye.book_library.application.user.command

import dev.starryeye.book_library.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ReturnBookService(
    private val userRepository: UserRepository,
) {

    fun returnBook(userId: Long, bookId: Long) {

        val user = userRepository.findByIdWithLoanHistories(userId)
            ?: throw IllegalArgumentException("user is not found, id = $userId")

        user.returnBook(bookId)
    }
}