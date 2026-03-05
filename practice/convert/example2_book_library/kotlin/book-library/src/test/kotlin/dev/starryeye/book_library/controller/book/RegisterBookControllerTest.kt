package dev.starryeye.book_library.controller.book

import com.ninjasquad.springmockk.MockkBean
import dev.starryeye.book_library.application.book.command.RegisterBookService
import dev.starryeye.book_library.domain.book.BookCategory
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

@WebMvcTest(controllers = [RegisterBookController::class])
class RegisterBookControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
) {

    @MockkBean(relaxed = true)
    private lateinit var registerBookService: RegisterBookService

    @DisplayName("POST /api/v1/books/new 요청으로 책을 등록한다.")
    @Test
    fun registerBook1() {

        // given
        val requestBody = mapOf(
            "bookname" to "book name 1",
            "category" to BookCategory.SCIENCE.name,
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/books/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isOk)
    }

    @DisplayName("책 이름은 필수인데 없으면 400 에러")
    @Test
    fun registerBook2() {

        // given
        val requestBody = mapOf(
            "category" to BookCategory.SCIENCE.name,
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/books/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @DisplayName("책 이름이 blank 이면 400 에러")
    @Test
    fun registerBook3() {

        // given
        val requestBody = mapOf(
            "bookname" to " ",
            "category" to BookCategory.SCIENCE.name,
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/books/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @DisplayName("책 카테고리는 필수인데 없으면 400 에러")
    @Test
    fun registerBook4() {

        // given
        val requestBody = mapOf(
            "bookname" to "book name 1",
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/books/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @DisplayName("책 카테고리가 BookCategory enum 값이 아니면 400 에러")
    @Test
    fun registerBook5() {

        // given
        val requestBody = mapOf(
            "bookname" to "book name 1",
            "category" to "XXX",
        )

        // when
        // then
        mockMvc.perform(
            post("/api/v1/books/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }
}