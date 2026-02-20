package dev.starryeye.book_library.application.book.query;

import dev.starryeye.book_library.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GetBookService {

    private final BookRepository bookRepository;

    public boolean existsBy(Long bookId) {
        return bookRepository.existsById(bookId);
    }
}
