package dev.starryeye.book_library.controller.user.response;

import dev.starryeye.book_library.application.command.result.RegisterUserResult;

public record UserResponse(
        Long id,
        String username,
        Integer age
) {

    public static UserResponse of(RegisterUserResult result) {
        return new UserResponse(
                result.id(),
                result.username(),
                result.age()
        );
    }
}
