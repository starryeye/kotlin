package dev.starryeye.book_library.controller.user.request;

public record ChangeUserNameRequest(
        Long id,
        String username
) {
}
