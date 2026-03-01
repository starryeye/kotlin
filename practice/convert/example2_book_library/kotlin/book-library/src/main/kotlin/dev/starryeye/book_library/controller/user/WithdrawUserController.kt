package dev.starryeye.book_library.controller.user

import dev.starryeye.book_library.application.user.command.WithdrawUserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class WithdrawUserController(
    private val service: WithdrawUserService
) {

    @DeleteMapping("/api/v1/users/")
    fun deleteUser(
        @RequestParam("id") id: Long
    ) {
        service.withdraw(id)
    }
}