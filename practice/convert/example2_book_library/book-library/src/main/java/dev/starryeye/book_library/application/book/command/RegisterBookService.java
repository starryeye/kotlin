package dev.starryeye.book_library.application.book.command;

import dev.starryeye.book_library.application.book.command.command.RegisterBookCommand;
import dev.starryeye.book_library.domain.book.Book;
import dev.starryeye.book_library.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class RegisterBookService {

    private final BookRepository bookRepository;

    public void register(RegisterBookCommand command) {

        Book book = Book.createBook(
                command.bookname()
        );

        bookRepository.save(book);
    }
}
