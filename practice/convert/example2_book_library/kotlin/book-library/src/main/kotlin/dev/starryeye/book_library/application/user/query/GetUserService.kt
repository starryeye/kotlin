package dev.starryeye.book_library.application.user.query

import dev.starryeye.book_library.application.user.query.result.GetUserResult
import dev.starryeye.book_library.domain.user.UserRepository
import dev.starryeye.book_library.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetUserService(
    private val userRepository: UserRepository,
) {

    fun getBy(userId: Long): GetUserResult {
        val user = userRepository.findByIdOrThrow(userId, "user is not found, id = $userId")

        return GetUserResult.of(user)
    }
}