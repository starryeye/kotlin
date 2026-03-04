package dev.starryeye.book_library.application.user.query

import dev.starryeye.book_library.domain.user.User
import dev.starryeye.book_library.domain.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GetUsersServiceTest @Autowired constructor(
    private val service: GetUsersService,
    private val userRepository: UserRepository,
) {

    @AfterEach
    fun tearDown() {
        userRepository.deleteAllInBatch()
    }

    @DisplayName("DB users 테이블에 존재하는 모든 user 를 조회한다.")
    @Test
    fun getAll() {

        // given
        userRepository.saveAll(listOf(
            User.fixture("A", 20),
            User.fixture("B", null),
        ))

        // when
        val result = service.getAll()

        // then
        assertThat(result.users).hasSize(2)
        assertThat(result.users).extracting("username", "age")
            .containsExactlyInAnyOrder(
                tuple("A", 20),
                tuple("B", null),
            )
    }
}