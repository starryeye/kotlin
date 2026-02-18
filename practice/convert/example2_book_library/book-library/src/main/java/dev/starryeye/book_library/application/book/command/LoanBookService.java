package dev.starryeye.book_library.application.book.command;

import dev.starryeye.book_library.application.book.command.command.LoanBookCommand;
import dev.starryeye.book_library.domain.book.Book;
import dev.starryeye.book_library.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class LoanBookService {

    private final BookRepository bookRepository;

    public void loan(LoanBookCommand command) {
        Book book = bookRepository.findById(command.bookId())
                .orElseThrow(() -> new IllegalArgumentException("book is not found, id = " + command.bookId()));

        book.loanTo(command.userId());
    }
}
