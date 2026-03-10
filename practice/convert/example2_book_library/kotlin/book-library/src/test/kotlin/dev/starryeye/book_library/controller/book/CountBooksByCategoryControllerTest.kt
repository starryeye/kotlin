package dev.starryeye.book_library.controller.book

import com.ninjasquad.springmockk.MockkBean
import dev.starryeye.book_library.application.book.query.CountBooksByCategoryService
import dev.starryeye.book_library.application.book.query.result.CountBookByCategoryResult
import dev.starryeye.book_library.application.book.query.result.CountBooksByCategoryResult
import dev.starryeye.book_library.domain.book.BookCategory
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

@WebMvcTest(controllers = [CountBooksByCategoryController::class])
class CountBooksByCategoryControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
) {

    @MockkBean(relaxed = true)
    private lateinit var countBooksByCategoryService: CountBooksByCategoryService

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @DisplayName("GET /api/v1/books/stats/category 요청으로 카테고리별 책 수를 조회한다.")
    @Test
    fun countBooksByCategory1() {

        // given
        every { countBooksByCategoryService.countBooksByCategory() } returns CountBooksByCategoryResult(
            stats = listOf(
                CountBookByCategoryResult(
                    category = BookCategory.SCIENCE,
                    count = 2
                ),
                CountBookByCategoryResult(
                    category = BookCategory.COMPUTER,
                    count = 1
                ),
            )
        )

        // when
        // then
        mockMvc.perform(get("/api/v1/books/stats/category"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.stats[0].category").value(BookCategory.SCIENCE.name))
            .andExpect(jsonPath("$.stats[0].count").value(2))
            .andExpect(jsonPath("$.stats[1].category").value(BookCategory.COMPUTER.name))
            .andExpect(jsonPath("$.stats[1].count").value(1))

        verify(exactly = 1) { countBooksByCategoryService.countBooksByCategory() }
    }

    @DisplayName("카테고리 통계가 비어있어도 응답한다.")
    @Test
    fun countBooksByCategory2() {

        // given
        every { countBooksByCategoryService.countBooksByCategory() } returns CountBooksByCategoryResult(
            stats = emptyList()
        )

        // when
        // then
        mockMvc.perform(get("/api/v1/books/stats/category"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.stats").isEmpty)
    }
}
