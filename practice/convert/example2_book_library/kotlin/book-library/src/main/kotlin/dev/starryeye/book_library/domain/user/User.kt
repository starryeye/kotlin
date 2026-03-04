package dev.starryeye.book_library.domain.user

import dev.starryeye.book_library.domain.BaseEntity
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistory
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener::class)
class User(

    var username: String,

    val age: Int?,
) : BaseEntity() {

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @field:OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val loanHistories: MutableList<UserLoanHistory> = mutableListOf()


    init {
        if (username.isBlank()) {
            throw IllegalArgumentException("Username cannot be blank.")
        }
    }

    fun changeUsername(username: String) {
        this.username = username
    }

    fun loanBook(bookId: Long) {
        loanHistories.add(UserLoanHistory(this, bookId, false))
    }

    fun returnBook(bookId: Long) {
        val history = loanHistories.asSequence()
            .filter { it.bookId == bookId }
            .firstOrNull { it.isNotReturned() }
            ?: throw IllegalArgumentException(
                "active loan history is not found, bookId = $bookId, userId = $id"
            )

        history.markReturned()
    }

    companion object {
        fun fixture(
            username: String = "user name",
            age: Int? = null,
        ): User {
            return User(
                username = username,
                age = age,
            )
        }
    }
}