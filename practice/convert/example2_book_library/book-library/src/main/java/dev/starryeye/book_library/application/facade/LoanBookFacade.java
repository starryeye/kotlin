package dev.starryeye.book_library.application.facade;

import dev.starryeye.book_library.application.book.command.LoanBookService;
import dev.starryeye.book_library.application.book.command.command.LoanBookCommand;
import dev.starryeye.book_library.application.facade.input.LoanBookInput;
import dev.starryeye.book_library.application.user.query.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor
public class LoanBookFacade {

    private final GetUserService getUserService;
    private final LoanBookService loanBookService;

    public void loan(LoanBookInput input) {
        getUserService.get(input.userId());
        loanBookService.loan(new LoanBookCommand(input.bookId(), input.userId()));
    }
}
