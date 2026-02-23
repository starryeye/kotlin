package dev.starryeye.book_library.domain.user

import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistory
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(

    var username: String,

    val age: Int?,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
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
}