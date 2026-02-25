package dev.starryeye.book_library.application.facade

import dev.starryeye.book_library.application.book.query.GetBookService
import dev.starryeye.book_library.application.facade.input.ReturnBookInput
import dev.starryeye.book_library.application.user.command.ReturnBookService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class ReturnBookFacade(
    private val getBookService: GetBookService,
    private val returnBookService: ReturnBookService,
) {

    fun returnBook(input: ReturnBookInput) {

        if (!getBookService.existsBy(input.bookId)) {
            throw IllegalArgumentException("book is not found, id = ${input.bookId}")
        }

        returnBookService.returnBook(userId = input.userId, bookId = input.bookId)
    }
}