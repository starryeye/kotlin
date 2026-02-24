package dev.starryeye.book_library.application.user.command

import dev.starryeye.book_library.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class WithdrawUserService(
    private val userRepository: UserRepository,
) {
}