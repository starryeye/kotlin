package dev.starryeye.book_library.application.user.command

import dev.starryeye.book_library.application.user.command.command.ChangeUsernameCommand
import dev.starryeye.book_library.domain.user.UserRepository
import dev.starryeye.book_library.util.fail
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ChangeUsernameService(
    private val userRepository: UserRepository,
) {

    fun changeUsername(command: ChangeUsernameCommand) {

        val user = userRepository.findByIdOrNull(command.id)
            ?: fail("user is not found, id = " + command.id)

        user.changeUsername(command.username)
    }
}