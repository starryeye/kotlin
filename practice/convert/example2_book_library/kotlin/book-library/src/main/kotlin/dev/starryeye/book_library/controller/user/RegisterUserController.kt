package dev.starryeye.book_library.controller.user

import dev.starryeye.book_library.application.user.command.RegisterUserService
import dev.starryeye.book_library.controller.user.request.RegisterUserRequest
import dev.starryeye.book_library.controller.user.request.toCommand
import dev.starryeye.book_library.controller.user.response.UserResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterUserController(
    private val service: RegisterUserService
) {

    @PostMapping("/api/v1/users/new")
    fun registerUser(
        @Valid @RequestBody request: RegisterUserRequest
    ): UserResponse {
        val result = service.register(request.toCommand())
        return UserResponse.of(result)
    }
}