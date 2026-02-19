package dev.starryeye.book_library.controller.book;

import dev.starryeye.book_library.application.facade.ReturnBookFacade;
import dev.starryeye.book_library.controller.book.request.ReturnBookRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReturnBookController {

    private final ReturnBookFacade facade;

    @PostMapping("/api/v1/books/return")
    public void returnBook(@Valid @RequestBody ReturnBookRequest request) {
        facade.returnBook(request.toInput());
    }
}
