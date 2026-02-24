package dev.starryeye.book_library.application.book.command

import dev.starryeye.book_library.application.book.command.command.RegisterBookCommand
import dev.starryeye.book_library.domain.book.Book
import dev.starryeye.book_library.domain.book.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class RegisterBookService(
    private val bookRepository: BookRepository,
) {

    fun register(command: RegisterBookCommand) {

        val book = Book(
            bookname = command.bookname
        )

        bookRepository.save(book)
    }
}