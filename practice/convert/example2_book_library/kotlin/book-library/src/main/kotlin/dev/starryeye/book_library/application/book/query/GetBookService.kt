package dev.starryeye.book_library.application.book.query

import dev.starryeye.book_library.domain.book.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetBookService(
    private val bookRepository: BookRepository,
) {

    fun existsBy(bookId: Long): Boolean {
        return bookRepository.existsById(bookId)
    }
}