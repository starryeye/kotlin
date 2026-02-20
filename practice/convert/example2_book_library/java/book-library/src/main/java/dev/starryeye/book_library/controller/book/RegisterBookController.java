package dev.starryeye.book_library.controller.book;

import dev.starryeye.book_library.application.book.command.RegisterBookService;
import dev.starryeye.book_library.controller.book.request.RegisterBookRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterBookController {

    private final RegisterBookService service;

    @PostMapping("/api/v1/books/new")
    public void registerBook(@Valid @RequestBody RegisterBookRequest request) {
        service.register(request.toCommand());
    }
}
