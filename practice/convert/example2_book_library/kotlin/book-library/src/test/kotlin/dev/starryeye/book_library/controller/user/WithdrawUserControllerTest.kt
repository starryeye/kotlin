package dev.starryeye.book_library.controller.user

import com.ninjasquad.springmockk.MockkBean
import dev.starryeye.book_library.application.user.command.WithdrawUserService
import io.mockk.clearAllMocks
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [WithdrawUserController::class])
class WithdrawUserControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
) {

    @MockkBean(relaxed = true)
    private lateinit var withdrawUserService: WithdrawUserService

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @DisplayName("DELETE /api/v1/users/?id=1 요청으로 유저를 삭제한다.")
    @Test
    fun withdrawUser1() {

        // given
        val withdrawUserId = "1"

        // when
        // then
        mockMvc.perform(delete("/api/v1/users/").param("id", withdrawUserId))
            .andDo(print())
            .andExpect(status().isOk)

        verify(exactly = 1) { withdrawUserService.withdraw(withdrawUserId.toLong()) }
    }

    @DisplayName("id 파라미터가 없으면 400 에러")
    @Test
    fun withdrawUser2() {

        // when
        // then
        mockMvc.perform(delete("/api/v1/users/"))
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @DisplayName("id 가 숫자가 아니면 400 에러")
    @Test
    fun withdrawUser3() {

        // given
        val withdrawUserId = "abc"

        // when
        // then
        mockMvc.perform(delete("/api/v1/users/").param("id", withdrawUserId))
            .andDo(print())
            .andExpect(status().isBadRequest)
    }
}
