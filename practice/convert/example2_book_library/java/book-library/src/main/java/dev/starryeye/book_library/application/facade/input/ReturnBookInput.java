package dev.starryeye.book_library.application.facade.input;

import java.util.Objects;

public record ReturnBookInput(
        Long bookId,
        Long userId
) {

    public ReturnBookInput {
        Objects.requireNonNull(bookId, "bookId must not be null");
        Objects.requireNonNull(userId, "userId must not be null");
    }
}
