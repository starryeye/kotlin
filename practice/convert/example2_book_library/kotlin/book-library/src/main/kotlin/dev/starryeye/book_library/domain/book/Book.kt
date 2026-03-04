package dev.starryeye.book_library.domain.book

import dev.starryeye.book_library.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "books")
@EntityListeners(AuditingEntityListener::class)
class Book(
    val bookname: String,
) : BaseEntity() {

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    init {
        if (bookname.isBlank()) {
            throw IllegalArgumentException("Book name cannot be null or blank")
        }
    }
}