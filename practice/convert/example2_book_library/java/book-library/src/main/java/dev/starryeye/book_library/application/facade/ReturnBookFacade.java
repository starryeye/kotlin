package dev.starryeye.book_library.application.facade;

import dev.starryeye.book_library.application.book.query.GetBookService;
import dev.starryeye.book_library.application.facade.input.ReturnBookInput;
import dev.starryeye.book_library.application.user.command.ReturnBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor
public class ReturnBookFacade {

    private final GetBookService getBookService;
    private final ReturnBookService returnBookService;

    public void returnBook(ReturnBookInput input) {

        if (!getBookService.existsBy(input.bookId())) {
            throw new IllegalArgumentException("book is not found, id = " + input.bookId());
        }

        returnBookService.returnBook(input.bookId(), input.userId());
    }
}
