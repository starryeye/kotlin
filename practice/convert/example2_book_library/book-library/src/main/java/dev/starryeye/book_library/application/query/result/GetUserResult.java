package dev.starryeye.book_library.application.query.result;

import dev.starryeye.book_library.domain.User;

public record GetUserResult(
        Long id,
        String username,
        Integer age
) {

    public static GetUserResult of(User user) {
        return new GetUserResult(
                user.getId(),
                user.getUsername(),
                user.getAge()
        );
    }
}
