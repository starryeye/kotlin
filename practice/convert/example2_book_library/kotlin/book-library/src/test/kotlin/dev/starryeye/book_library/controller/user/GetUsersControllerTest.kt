package dev.starryeye.book_library.controller.user

import com.ninjasquad.springmockk.MockkBean
import dev.starryeye.book_library.application.user.query.GetUsersService
import dev.starryeye.book_library.application.user.query.result.GetUserResult
import dev.starryeye.book_library.application.user.query.result.GetUsersResult
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import tools.jackson.databind.ObjectMapper


@WebMvcTest(controllers = [GetUsersController::class])
class GetUsersControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
) {

    @MockkBean(relaxed = true)
    private lateinit var getUsersService: GetUsersService

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @DisplayName("GET /api/v1/users 요청으로 전체 유저를 조회한다.")
    @Test
    fun getUsers1() {

        // given
        every { getUsersService.getAll() } returns GetUsersResult(
            users = listOf(
                GetUserResult(
                    id = 1L,
                    username = "user name 1",
                    age = 20,
                ),
                GetUserResult(
                    id = 2L,
                    username = "user name 2",
                    age = 30,
                ),
            )
        )

        // when
        // then
        mockMvc.perform(
            get("/api/v1/users")
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.users[0].id").value(1L))
            .andExpect(jsonPath("$.users[0].username").value("user name 1"))
            .andExpect(jsonPath("$.users[0].age").value(20))
            .andExpect(jsonPath("$.users[1].id").value(2L))
            .andExpect(jsonPath("$.users[1].username").value("user name 2"))
            .andExpect(jsonPath("$.users[1].age").value(30))

        verify(exactly = 1) { getUsersService.getAll() }
    }

    @DisplayName("age 가 null 인 유저도 조회된다.")
    @Test
    fun getUsers2() {

        // given
        every { getUsersService.getAll() } returns GetUsersResult(
            users = listOf(
                GetUserResult(
                    id = 1L,
                    username = "user name 1",
                    age = null,
                ),
            )
        )

        // when
        // then
        mockMvc.perform(get("/api/v1/users"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.users[0].id").value(1L))
            .andExpect(jsonPath("$.users[0].username").value("user name 1"))
            .andExpect(jsonPath("$.users[0].age").isEmpty)
    }
}