package dev.starryeye.book_library.application.user.command

import dev.starryeye.book_library.domain.user.User
import dev.starryeye.book_library.domain.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WithdrawUserServiceTest @Autowired constructor(
    private val service: WithdrawUserService,
    private val userRepository: UserRepository,
) {

    @AfterEach
    fun tearDown() {
        userRepository.deleteAllInBatch()
    }

    @DisplayName("userId 를 전달하면 해당 유저는 DB 에서 삭제된다.")
    @Test
    fun withdraw() {

        // given
        val saved = userRepository.save(User("A", 20))
        val deleteUserId = saved.id

        // when
        service.withdraw(deleteUserId!!)

        // then
        val result = userRepository.findAll()
        assertThat(result).isEmpty()
    }
}