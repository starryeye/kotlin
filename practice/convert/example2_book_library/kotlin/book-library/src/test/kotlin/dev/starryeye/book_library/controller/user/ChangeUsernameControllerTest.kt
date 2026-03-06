package dev.starryeye.book_library.controller.user

import com.ninjasquad.springmockk.MockkBean
import dev.starryeye.book_library.application.user.command.ChangeUsernameService
import dev.starryeye.book_library.application.user.command.command.ChangeUsernameCommand
import dev.starryeye.book_library.controller.user.request.ChangeUsernameRequest
import io.mockk.clearAllMocks
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import tools.jackson.databind.ObjectMapper

@WebMvcTest(controllers = [ChangeUsernameController::class])
class ChangeUsernameControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
) {

    @MockkBean(relaxed = true)
    private lateinit var changeUsernameService: ChangeUsernameService

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @DisplayName("PUT /api/v1/users/change-name 요청으로 유저 이름을 변경한다.")
    @Test
    fun changeUsername1() {

        // given
        val requestBody = ChangeUsernameRequest(1L, "new-username")

        // when
        // then
        mockMvc.perform(
            put("/api/v1/users/change-name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isOk)

        verify(exactly = 1) {
            changeUsernameService.changeUsername(
                ChangeUsernameCommand(
                    id = requestBody.id!!,
                    username = requestBody.username!!
                )
            )
        }
    }

    @DisplayName("id 는 필수인데 없으면 400 에러")
    @Test
    fun changeUsername2() {

        // given
        val requestBody = ChangeUsernameRequest(null, "new-username")

        // when
        // then
        mockMvc.perform(
            put("/api/v1/users/change-name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @DisplayName("username 이 null 이면 400 에러")
    @Test
    fun changeUsername3() {

        // given
        val requestBody = ChangeUsernameRequest(1L, null)

        // when
        // then
        mockMvc.perform(
            put("/api/v1/users/change-name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @DisplayName("username 이 blank 이면 400 에러")
    @Test
    fun changeUsername4() {

        // given
        val requestBody = ChangeUsernameRequest(1L, " ")

        // when
        // then
        mockMvc.perform(
            put("/api/v1/users/change-name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }
}