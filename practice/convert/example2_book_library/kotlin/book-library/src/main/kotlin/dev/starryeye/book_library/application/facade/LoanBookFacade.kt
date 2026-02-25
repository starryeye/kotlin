package dev.starryeye.book_library.application.facade

import dev.starryeye.book_library.application.book.query.GetBookService
import dev.starryeye.book_library.application.facade.input.LoanBookInput
import dev.starryeye.book_library.application.user.command.LoanBookService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class LoanBookFacade(
    private val getBookService: GetBookService,
    private val loanBookService: LoanBookService,
) {

    fun loanBook(input: LoanBookInput) {

        if (!getBookService.existsBy(bookId = input.bookId)) {
            throw IllegalArgumentException("book is not found, id = ${input.bookId}")
        }

        loanBookService.loan(userId = input.userId, bookId = input.bookId)
    }
}