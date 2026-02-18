package dev.starryeye.book_library.controller.user.response;

import dev.starryeye.book_library.application.user.query.result.GetUsersResult;

import java.util.List;

public record UsersResponse(
        List<UserResponse> users
) {

    public static UsersResponse of(GetUsersResult result) {
        return new UsersResponse(
                result.users().stream()
                        .map(userResult -> new UserResponse(
                                userResult.id(),
                                userResult.username(),
                                userResult.age()
                        ))
                        .toList()
        );
    }
}
