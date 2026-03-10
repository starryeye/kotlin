package dev.starryeye.book_library.controller.book

import com.ninjasquad.springmockk.MockkBean
import dev.starryeye.book_library.application.book.query.CountLoanedBooksService
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [CountLoanedBooksController::class])
class CountLoanedBooksControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
) {

    @MockkBean(relaxed = true)
    private lateinit var countLoanedBooksService: CountLoanedBooksService

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @DisplayName("GET /api/v1/books/loan-count 요청으로 대출중인 책 수를 조회한다.")
    @Test
    fun countLoanedBooks1() {

        // given
        every { countLoanedBooksService.countLoanedBooks() } returns 3

        // when
        // then
        mockMvc.perform(get("/api/v1/books/loan-count"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(content().string("3"))

        verify(exactly = 1) { countLoanedBooksService.countLoanedBooks() }
    }

    @DisplayName("대출중인 책이 없으면 0 을 응답한다.")
    @Test
    fun countLoanedBooks2() {

        // given
        every { countLoanedBooksService.countLoanedBooks() } returns 0

        // when
        // then
        mockMvc.perform(get("/api/v1/books/loan-count"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(content().string("0"))
    }
}
