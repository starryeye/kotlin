package dev.starryeye.book_library.application.book.command.command

data class RegisterBookCommand(
    val bookname: String,
    val category: String,
) {

    init {
        require(bookname.isNotBlank()) { "bookname must not be blank" }
        require(category.isNotBlank()) { "category must not be blank" }
    }
}