package dev.starryeye.book_library.domain.book

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "books")
class Book(
    val name: String?,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    init {
        if (name.isNullOrBlank()) {
            throw IllegalArgumentException("Book name cannot be null or blank")
        }
    }
}