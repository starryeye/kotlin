package dev.starryeye.book_library.domain.user.loan_history

import dev.starryeye.book_library.domain.user.User
import jakarta.persistence.*

@Entity
@Table(name = "user_loan_history")
class UserLoanHistory(
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    val bookId: Long,

    var isReturn: Boolean,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun markReturned() {
        if (isReturn) {
            throw IllegalStateException("already returned, id=$id")
        }
        this.isReturn = true
    }

    fun isNotReturned(): Boolean = !isReturn
}