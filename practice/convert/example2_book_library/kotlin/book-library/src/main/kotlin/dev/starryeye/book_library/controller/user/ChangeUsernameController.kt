package dev.starryeye.book_library.controller.user

import dev.starryeye.book_library.application.user.command.ChangeUsernameService
import dev.starryeye.book_library.controller.user.request.ChangeUsernameRequest
import dev.starryeye.book_library.controller.user.request.toCommand
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ChangeUsernameController(
    private val service: ChangeUsernameService,
) {

    @PutMapping("/api/v1/users/change-name")
    fun changeUsername(
        @Valid @RequestBody request: ChangeUsernameRequest,
    ) {
        service.changeUsername(request.toCommand())
    }
}