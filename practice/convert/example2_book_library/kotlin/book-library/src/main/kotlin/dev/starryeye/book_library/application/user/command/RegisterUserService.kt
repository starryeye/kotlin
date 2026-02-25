package dev.starryeye.book_library.application.user.command

import dev.starryeye.book_library.application.user.command.command.RegisterUserCommand
import dev.starryeye.book_library.application.user.command.result.RegisterUserResult
import dev.starryeye.book_library.domain.user.User
import dev.starryeye.book_library.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class RegisterUserService(
    private val userRepository: UserRepository,
) {

    fun register(command: RegisterUserCommand): RegisterUserResult {

        val user = User(
            username = command.username,
            age = command.age,
        )

        userRepository.save(user)

        return RegisterUserResult.of(user)
    }
}