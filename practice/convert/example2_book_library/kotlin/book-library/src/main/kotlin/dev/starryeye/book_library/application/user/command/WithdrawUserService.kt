package dev.starryeye.book_library.application.user.command

import dev.starryeye.book_library.domain.user.UserRepository
import dev.starryeye.book_library.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class WithdrawUserService(
    private val userRepository: UserRepository,
) {

    fun withdraw(id: Long) {

        val user = userRepository.findByIdOrThrow(id, "user is not found, id = $id")

        userRepository.delete(user)
    }
}