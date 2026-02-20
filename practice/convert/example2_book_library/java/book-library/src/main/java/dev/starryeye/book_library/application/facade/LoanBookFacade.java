package dev.starryeye.book_library.application.facade;

import dev.starryeye.book_library.application.book.query.GetBookService;
import dev.starryeye.book_library.application.facade.input.LoanBookInput;
import dev.starryeye.book_library.application.user.command.LoanBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor
public class LoanBookFacade {

    private final GetBookService getBookService;
    private final LoanBookService loanBookService;

    public void loan(LoanBookInput input) {
        if (!getBookService.existsBy(input.bookId())) {
            throw new IllegalArgumentException("book is not found, id = " + input.bookId());
        }

        loanBookService.loan(input.userId(), input.bookId());
    }
}
