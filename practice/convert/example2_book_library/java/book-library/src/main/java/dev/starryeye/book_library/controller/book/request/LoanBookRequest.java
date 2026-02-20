package dev.starryeye.book_library.controller.book.request;

import dev.starryeye.book_library.application.facade.input.LoanBookInput;
import jakarta.validation.constraints.NotNull;

public record LoanBookRequest(
        @NotNull(message = "bookId must not be null")
        Long bookId,
        @NotNull(message = "userId must not be null")
        Long userId
) {

    public LoanBookInput toInput() {
        return new LoanBookInput(bookId, userId);
    }
}
