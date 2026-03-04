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
    val category: String,
) : BaseEntity() {

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    init {
        if (bookname.isBlank()) {
            throw IllegalArgumentException("Book name cannot be null or blank")
        }
    }

    // object mother 패턴..
    // 해당 객체(Book)의 프로퍼티가 늘어나더라도 Test code 에 전파되지 않는다.
    //      test code 에서는 Book 객체 생성시 Book.fixture() 만을 사용
    // 참고. DTO 는 Domain 객체보다 많이 사용되지 않는 편이라 fixture 를 안만드는 편
    companion object {
        fun fixture(
            bookname: String = "book name",
            category: String = "book category",
        ): Book {
            return Book(
                bookname = bookname,
                category = category,
            )
        }
    }
}