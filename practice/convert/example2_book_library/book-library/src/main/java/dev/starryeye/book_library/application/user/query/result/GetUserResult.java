package dev.starryeye.book_library.application.user.query.result;

import dev.starryeye.book_library.domain.user.User;

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
