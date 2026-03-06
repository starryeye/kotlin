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

    @field:Enumerated(EnumType.STRING)
    var status: UserLoanStatus = UserLoanStatus.LOANED,
    /**
     * 여러 boolean 필드가 상태를 나타낼 때 코드가 복잡해 질 수 있다.
     * boolean 프로퍼티가 n 개 이면 해당 도메인의 상태 경우의 수는 2^n 이다.
     *      그와중에 있을 수 없는 상태(필요없는 상태)의 조합도 생긴다.
     * 따라서, 상태는 enum 으로 명확하게 관리하는 편이 좋다.
     * ex. User 의 경우엔..
     *      isActive, isDeleted 두개로 하지말고
     *      하나의 status 로 아래와 같이 하나의 상태로 관리
     *          ACTIVE(활성유저), IN_ACTIVE(휴면유저), DELETED(탈퇴유저)
     */

) : BaseEntity() {

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun markReturned() {
        if (status == UserLoanStatus.RETURNED) {
            throw IllegalStateException("already returned, id=$id")
        }
        this.status = UserLoanStatus.RETURNED
    }

    fun isNotReturned(): Boolean = status == UserLoanStatus.LOANED

    companion object {
        fun fixture(
            user: User = User.fixture(),
            bookId: Long = 1L,
            status: UserLoanStatus = UserLoanStatus.LOANED,
        ): UserLoanHistory {
            return UserLoanHistory(
                user = user,
                bookId = bookId,
                status = status,
            )
        }
    }
}
