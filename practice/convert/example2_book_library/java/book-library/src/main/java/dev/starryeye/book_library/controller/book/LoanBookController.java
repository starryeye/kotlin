package dev.starryeye.book_library.controller.book;

import dev.starryeye.book_library.application.facade.LoanBookFacade;
import dev.starryeye.book_library.controller.book.request.LoanBookRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoanBookController {

    private final LoanBookFacade facade;

    @PostMapping("/api/v1/books/loan")
    public void loanBook(@Valid @RequestBody LoanBookRequest request) {
        facade.loan(request.toInput());
    }
}
