package dev.starryeye.book_library.domain.user.loan_history

import dev.starryeye.book_library.domain.BaseEntity
import dev.starryeye.book_library.domain.user.User
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "user_loan_history")
@EntityListeners(AuditingEntityListener::class)
class UserLoanHistory(
    @field:ManyToOne(fetch = FetchType.LAZY, optional = false)
    @field:JoinColumn(name = "user_id", nullable = false)
    val user: User,

    val bookId: Long,

    var isReturn: Boolean,
) : BaseEntity() {

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun markReturned() {
        if (isReturn) {
            throw IllegalStateException("already returned, id=$id")
        }
        this.isReturn = true
    }

    fun isNotReturned(): Boolean = !isReturn
}