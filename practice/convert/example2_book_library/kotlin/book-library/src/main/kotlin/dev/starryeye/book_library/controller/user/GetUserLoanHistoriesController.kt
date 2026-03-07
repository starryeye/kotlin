package dev.starryeye.book_library.controller.user

import dev.starryeye.book_library.application.user.query.GetUserLoanHistoriesService
import dev.starryeye.book_library.controller.user.response.UserLoanHistoriesResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetUserLoanHistoriesController(
    private val service: GetUserLoanHistoriesService
) {

    @GetMapping("/api/v1/users/loan")
    fun getUserLoanHistories(): UserLoanHistoriesResponse {
        val result = service.getUserLoanHistories()
        return UserLoanHistoriesResponse.of(result)
    }
}