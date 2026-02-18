package dev.starryeye.book_library.application.user.command.command;

import java.util.Objects;

public record ChangeUsernameCommand(
        Long id,
        String username
) {

    public ChangeUsernameCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(username, "username must not be null");
        if (username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }
    }
}
