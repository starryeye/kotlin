package dev.starryeye.book_library.application.book.command

import dev.starryeye.book_library.domain.book.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class RegisterBookService(
    private val bookRepository: BookRepository,
) {
}