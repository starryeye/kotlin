package dev.starryeye.book_library.application.book.command.command

data class RegisterBookCommand(
    val bookname: String
) {

    init {
        require(bookname.isNotBlank()) { "bookname must not be blank" }
    }
}