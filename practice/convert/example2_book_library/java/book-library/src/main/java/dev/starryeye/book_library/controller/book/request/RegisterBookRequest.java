package dev.starryeye.book_library.controller.book.request;

import dev.starryeye.book_library.application.book.command.command.RegisterBookCommand;
import jakarta.validation.constraints.NotBlank;

public record RegisterBookRequest(
        @NotBlank(message = "bookname must not be blank")
        String bookname
) {

    public RegisterBookCommand toCommand() {
        return new RegisterBookCommand(bookname);
    }
}
