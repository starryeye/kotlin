package dev.starryeye.book_library.controller.user.request;

import dev.starryeye.book_library.application.command.command.RegisterUserCommand;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequest(
        @NotBlank(message = "username must not be blank")
        String username,
        Integer age
) {

    public RegisterUserCommand toCommand() {
        return new RegisterUserCommand(username, age);
    }
}
