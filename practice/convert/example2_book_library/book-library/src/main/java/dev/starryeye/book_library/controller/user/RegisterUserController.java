package dev.starryeye.book_library.controller.user;

import dev.starryeye.book_library.application.command.result.RegisterUserResult;
import dev.starryeye.book_library.controller.user.request.RegisterUserRequest;
import dev.starryeye.book_library.controller.user.response.UserResponse;
import dev.starryeye.book_library.application.command.RegisterUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterUserController {

    private final RegisterUserService service;

    @PostMapping("/api/v1/users/new")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        RegisterUserResult result = service.register(request.toCommand());
        UserResponse response = UserResponse.of(result);
        return ResponseEntity.ok(response);
    }
}
