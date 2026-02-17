package dev.starryeye.book_library.controller.user.request;

import dev.starryeye.book_library.application.command.command.ChangeUsernameCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChangeUsernameRequest(
        @NotNull(message = "id must not be null")
        Long id,
        @NotBlank(message = "username must not be blank")
        String username
) {

    public ChangeUsernameCommand toCommand() {
        return new ChangeUsernameCommand(id, username);
    }
}
