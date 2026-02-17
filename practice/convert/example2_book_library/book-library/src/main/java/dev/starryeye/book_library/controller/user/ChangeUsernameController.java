package dev.starryeye.book_library.controller.user;

import dev.starryeye.book_library.application.command.ChangeUsernameService;
import dev.starryeye.book_library.controller.user.request.ChangeUsernameRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChangeUsernameController {

    private final ChangeUsernameService service;

    @PutMapping("/api/v1/users/change-name")
    public void changeUsername(@Valid @RequestBody ChangeUsernameRequest request) {
        service.change(request.toCommand());
    }
}
