package dev.starryeye.book_library.application.user.query

import dev.starryeye.book_library.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetUserService(
    private val userRepository: UserRepository,
) {
}