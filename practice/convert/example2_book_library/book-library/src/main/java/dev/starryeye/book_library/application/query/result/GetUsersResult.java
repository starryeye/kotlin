package dev.starryeye.book_library.application.query.result;

import dev.starryeye.book_library.domain.User;

import java.util.List;

public record GetUsersResult(
        List<GetUserResult> users
) {

    public static GetUsersResult of(List<User> users) {
        return new GetUsersResult(
                users.stream()
                        .map(user -> new GetUserResult(
                                user.getId(),
                                user.getUsername(),
                                user.getAge()
                        ))
                        .toList()
        );
    }
}
