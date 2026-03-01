package dev.starryeye.book_library.controller.user

import dev.starryeye.book_library.application.user.query.GetUsersService
import dev.starryeye.book_library.controller.user.response.UsersResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetUsersController(
    private val service: GetUsersService,
) {

    @GetMapping("/api/v1/users")
    fun getUsers(): UsersResponse {
        val result = service.getAll()
        return UsersResponse.of(result)
    }
}