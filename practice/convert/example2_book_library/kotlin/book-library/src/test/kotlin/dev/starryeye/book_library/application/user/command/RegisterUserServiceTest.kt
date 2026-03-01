package dev.starryeye.book_library.application.user.command

import dev.starryeye.book_library.application.user.command.command.RegisterUserCommand
import dev.starryeye.book_library.domain.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RegisterUserServiceTest @Autowired constructor(
    private val service: RegisterUserService,
    private val repository: UserRepository,
) {

    @AfterEach
    fun tearDown() {
        repository.deleteAllInBatch()
    }

    @DisplayName("이름과 나이를 받아 유저를 등록한다.")
    @Test
    fun registerUser1() {
        // given
        val command: RegisterUserCommand = RegisterUserCommand("A", age = 22)

        // when
        val result = service.register(command)

        // then
        assertThat(result.id).isNotNull
        assertThat(result.username).isEqualTo(command.username)
        assertThat(result.age).isEqualTo(command.age)
    }

    @DisplayName("유저를 등록하면 DB에 저장된다.")
    @Test
    fun registerUser2() {
        // given
        val command: RegisterUserCommand = RegisterUserCommand("A", age = 22)

        // when
        val result = service.register(command)

        // then
        val users = repository.findAll()
        assertThat(users).hasSize(1)
        assertThat(users[0].username).isEqualTo(command.username)
        assertThat(users[0].age).isEqualTo(command.age)
    }

    @DisplayName("나이가 없어도 유저를 등록할 수 있다.")
    @Test
    fun registerUser3() {
        // given
        val command: RegisterUserCommand = RegisterUserCommand("A", age = null)

        // when
        val result = service.register(command)

        // then
        val users = repository.findAll()
        assertThat(users).hasSize(1)
        assertThat(users[0].username).isEqualTo(command.username)
        assertThat(users[0].age).isEqualTo(command.age)
    }
}