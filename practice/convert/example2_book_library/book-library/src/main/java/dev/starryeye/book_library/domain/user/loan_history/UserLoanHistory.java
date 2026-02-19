package dev.starryeye.book_library.domain.user.loan_history;

import dev.starryeye.book_library.domain.BaseEntity;
import dev.starryeye.book_library.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "user_loan_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = AuditingEntityListener.class)
public class UserLoanHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Long bookId;

    private Boolean isReturn;

    @Builder
    private UserLoanHistory(Long id, User user, Long bookId, Boolean isReturn) {
        if (user == null) {
            throw new IllegalArgumentException("user must not be null");
        }
        if (bookId == null) {
            throw new IllegalArgumentException("bookId must not be null");
        }
        if (isReturn == null) {
            throw new IllegalArgumentException("loanedAt must not be null");
        }

        this.id = id;
        this.user = user;
        this.bookId = bookId;
        this.isReturn = isReturn;
    }

    public static UserLoanHistory create(User user, Long bookId) {
        return UserLoanHistory.builder()
                .id(null)
                .user(user)
                .bookId(bookId)
                .isReturn(false)
                .build();
    }

    public void markReturned() {
        if (isReturn == null) {
            throw new IllegalStateException("history status is wrong.., id = " + id);
        }
        this.isReturn = true;
    }

    public boolean isNotReturned() {
        return !this.isReturn;
    }
}
