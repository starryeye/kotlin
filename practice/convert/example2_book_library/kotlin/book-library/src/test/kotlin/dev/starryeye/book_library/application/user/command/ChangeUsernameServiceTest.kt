package dev.starryeye.book_library.application.user.command

import dev.starryeye.book_library.application.user.command.command.ChangeUsernameCommand
import dev.starryeye.book_library.domain.user.User
import dev.starryeye.book_library.domain.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class ChangeUsernameServiceTest @Autowired constructor(
    private val service: ChangeUsernameService,
    private val userRepository: UserRepository,
) {

    @AfterEach
    fun tearDown() {
        userRepository.deleteAllInBatch()
    }

    @DisplayName("userId 와 변경할 이름을 전달하면 이름 변경이 DB 에 업데이트 된다.")
    @Test
    fun changeUsername() {

        // given
        val saved = userRepository.save(User("A", 20))
        val command = ChangeUsernameCommand(
            id = saved.id!!,
            username = "B",
        )

        // when
        service.changeUsername(command)

        // then
        val result = userRepository.findByIdOrNull(saved.id!!)
        assertThat(result).isNotNull
        assertThat(result!!.username).isEqualTo(command.username)
    }
}