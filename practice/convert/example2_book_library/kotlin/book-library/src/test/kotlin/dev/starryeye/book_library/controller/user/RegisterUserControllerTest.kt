package dev.starryeye.book_library.controller.user

import com.ninjasquad.springmockk.MockkBean
import dev.starryeye.book_library.application.user.command.RegisterUserService
import dev.starryeye.book_library.application.user.command.command.RegisterUserCommand
import dev.starryeye.book_library.application.user.command.result.RegisterUserResult
import dev.starryeye.book_library.controller.user.request.RegisterUserRequest
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import tools.jackson.databind.ObjectMapper

@WebMvcTest(controllers = [RegisterUserController::class])
class RegisterUserControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
) {

    @MockkBean(relaxed = true)
    private lateinit var registerUserService: RegisterUserService

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @DisplayName("POST /api/v1/users/new 요청으로 유저를 등록한다.")
    @Test
    fun registerUser1() {

        // given
        val requestBody = RegisterUserRequest("new-username", 20)
        every {
            registerUserService.register(
                RegisterUserCommand(
                    username = requestBody.username!!,
                    age = requestBody.age
                )
            )
        } returns RegisterUserResult(id = 1L, username = requestBody.username!!, age = requestBody.age)

        // when
        // then
        mockMvc.perform(
            post("/api/v1/users/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.username").value(requestBody.username))
            .andExpect(jsonPath("$.age").value(20))

        verify(exactly = 1) {
            registerUserService.register(
                RegisterUserCommand(
                    username = requestBody.username,
                    age = requestBody.age
                )
            )
        }
    }

    @DisplayName("username 은 필수인데 없으면 400 에러")
    @Test
    fun registerUser2() {

        // given
        val requestBody = RegisterUserRequest(null, 20)

        // when
        // then
        mockMvc.perform(
            post("/api/v1/users/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @DisplayName("username 이 blank 이면 400 에러")
    @Test
    fun registerUser3() {

        // given
        val requestBody = RegisterUserRequest(" ", 20)

        // when
        // then
        mockMvc.perform(
            post("/api/v1/users/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }
}
