package dev.starryeye.book_library

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookLibraryApplication

fun main(args: Array<String>) {
	runApplication<BookLibraryApplication>(*args)
}
