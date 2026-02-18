package dev.starryeye.book_library.application.user.command.command;

import java.util.Objects;

public record RegisterUserCommand(
        String username,
        Integer age
) {

    public RegisterUserCommand {
        Objects.requireNonNull(username, "username must not be null");
        if (username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }
    }
}
