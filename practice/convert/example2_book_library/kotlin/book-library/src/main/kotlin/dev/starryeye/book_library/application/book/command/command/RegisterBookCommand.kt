package dev.starryeye.book_library.application.book.command.command

import dev.starryeye.book_library.domain.book.BookCategory

data class RegisterBookCommand(
    val bookname: String,
    val category: BookCategory,
) {

    init {
        require(bookname.isNotBlank()) { "bookname must not be blank" }
    }
}