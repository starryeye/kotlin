package dev.starryeye.book_library.controller.user;

import dev.starryeye.book_library.application.command.WithdrawUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WithdrawUserController {

    private final WithdrawUserService service;

    @DeleteMapping("/api/v1/users")
    public void withdrawUser(@RequestParam("id") Long id) {
        service.withdraw(id);
    }
}
