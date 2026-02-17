package dev.starryeye.book_library.application.command.result;

import dev.starryeye.book_library.domain.user.User;

public record RegisterUserResult(
        Long id,
        String username,
        Integer age
) {

    public static RegisterUserResult of(User user) {
        return new RegisterUserResult(
                user.getId(),
                user.getUsername(),
                user.getAge()
        );
    }
}
