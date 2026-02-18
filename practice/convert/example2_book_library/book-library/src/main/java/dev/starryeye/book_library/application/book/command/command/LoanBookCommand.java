package dev.starryeye.book_library.application.book.command.command;

import java.util.Objects;

public record LoanBookCommand(
        Long bookId,
        Long userId
) {

    public LoanBookCommand {
        Objects.requireNonNull(bookId, "bookId must not be null");
        Objects.requireNonNull(userId, "userId must not be null");
    }
}
