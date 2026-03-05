package dev.starryeye.book_library.controller.book

import com.ninjasquad.springmockk.MockkBean
import dev.starryeye.book_library.application.facade.LoanBookFacade
import dev.starryeye.book_library.application.facade.input.LoanBookInput
import io.mockk.clearAllMocks
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import tools.jackson.databind.ObjectMapper

@WebMvcTest(controllers = [LoanBookController::class])
class LoanBookControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
) {

    @MockkBean(relaxed = true)
    private lateinit var loanBookFacade: LoanBookFacade

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @DisplayName("POST /api/v1/books/loan 요청으로 책을 대출한다.")
    @Test
    fun loanBook1() {

        // given
        val requestBody = mapOf(
            "bookId" to 1L,
            "userId" to 2L,
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/books/loan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isOk)

        verify(exactly = 1) {
            loanBookFacade.loanBook(LoanBookInput(bookId = 1L, userId = 2L))
        }
    }

    @DisplayName("bookId 는 필수인데 없으면 400 에러")
    @Test
    fun loanBook2() {

        // given
        val requestBody = mapOf(
            "userId" to 2L,
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/books/loan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @DisplayName("userId 는 필수인데 없으면 400 에러")
    @Test
    fun loanBook3() {

        // given
        val requestBody = mapOf(
            "bookId" to 1L,
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/books/loan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }
}
