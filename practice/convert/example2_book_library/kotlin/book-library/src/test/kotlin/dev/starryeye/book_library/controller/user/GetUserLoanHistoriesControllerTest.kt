package dev.starryeye.book_library.controller.user

import com.ninjasquad.springmockk.MockkBean
import dev.starryeye.book_library.application.user.query.GetUserLoanHistoriesService
import dev.starryeye.book_library.application.user.query.result.BookHistoryResult
import dev.starryeye.book_library.application.user.query.result.GetUserLoanHistoriesResult
import dev.starryeye.book_library.application.user.query.result.UserLoanHistoryResult
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [GetUserLoanHistoriesController::class])
class GetUserLoanHistoriesControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
) {

    @MockkBean(relaxed = true)
    private lateinit var getUserLoanHistoriesService: GetUserLoanHistoriesService

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @DisplayName("GET /api/v1/users/loan 요청으로 유저별 대출 이력을 조회한다.")
    @Test
    fun getUserLoanHistories1() {

        // given
        every { getUserLoanHistoriesService.getUserLoanHistories() } returns GetUserLoanHistoriesResult(
            histories = listOf(
                UserLoanHistoryResult(
                    userId = 1L,
                    username = "A",
                    books = listOf(
                        BookHistoryResult(
                            bookId = 10L,
                            bookname = "book-1",
                            isReturned = false
                        ),
                        BookHistoryResult(
                            bookId = 11L,
                            bookname = "book-2",
                            isReturned = true
                        ),
                    )
                )
            )
        )

        // when
        // then
        mockMvc.perform(get("/api/v1/users/loan"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.histories[0].userId").value(1L))
            .andExpect(jsonPath("$.histories[0].username").value("A"))
            .andExpect(jsonPath("$.histories[0].books[0].bookId").value(10L))
            .andExpect(jsonPath("$.histories[0].books[0].bookname").value("book-1"))
            .andExpect(jsonPath("$.histories[0].books[0].isReturned").value(false))
            .andExpect(jsonPath("$.histories[0].books[1].bookId").value(11L))
            .andExpect(jsonPath("$.histories[0].books[1].bookname").value("book-2"))
            .andExpect(jsonPath("$.histories[0].books[1].isReturned").value(true))

        verify(exactly = 1) { getUserLoanHistoriesService.getUserLoanHistories() }
    }

    @DisplayName("대출 이력이 비어있는 유저도 조회한다.")
    @Test
    fun getUserLoanHistories2() {

        // given
        every { getUserLoanHistoriesService.getUserLoanHistories() } returns GetUserLoanHistoriesResult(
            histories = listOf(
                UserLoanHistoryResult(
                    userId = 1L,
                    username = "A",
                    books = emptyList()
                )
            )
        )

        // when
        // then
        mockMvc.perform(get("/api/v1/users/loan"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.histories[0].userId").value(1L))
            .andExpect(jsonPath("$.histories[0].username").value("A"))
            .andExpect(jsonPath("$.histories[0].books").isEmpty)
    }
}
