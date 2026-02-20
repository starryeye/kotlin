package dev.starryeye.book_library.controller.book.request;

import dev.starryeye.book_library.application.facade.input.ReturnBookInput;
import jakarta.validation.constraints.NotNull;

public record ReturnBookRequest(
        @NotNull(message = "bookId must not be null")
        Long bookId,
        @NotNull(message = "userId must not be null")
        Long userId
) {

    public ReturnBookInput toInput() {
        return new ReturnBookInput(bookId, userId);
    }
}
