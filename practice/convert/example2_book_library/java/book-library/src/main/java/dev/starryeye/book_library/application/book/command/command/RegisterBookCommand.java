package dev.starryeye.book_library.application.book.command.command;

import java.util.Objects;

public record RegisterBookCommand(
        String bookname
) {

    public RegisterBookCommand {
        Objects.requireNonNull(bookname, "bookname must not be null");
        if (bookname.isBlank()) {
            throw new IllegalArgumentException("bookname must not be blank");
        }
    }
}
