package dev.starryeye.book_library.application.user.query

import dev.starryeye.book_library.application.user.query.result.GetUsersResult
import dev.starryeye.book_library.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetUsersService(
    private val userRepository: UserRepository,
) {

    fun getAll(): GetUsersResult {
        val users = userRepository.findAll()

        return GetUsersResult.of(users)
    }
}