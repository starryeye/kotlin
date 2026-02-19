package dev.starryeye.book_library.domain.user;

import dev.starryeye.book_library.domain.BaseEntity;
import dev.starryeye.book_library.domain.user.loan_history.UserLoanHistory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = AuditingEntityListener.class)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLoanHistory> loanHistories = new ArrayList<>();

    @Builder
    private User(Long id, String username, Integer age) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }

        this.id = id;
        this.username = username;
        this.age = age;
    }

    public static User createUser(String username, Integer age) {
        return User.builder()
                .id(null)
                .username(username)
                .age(age)
                .build();
    }

    public void changeUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }
        this.username = username;
    }

    public void loanBook(Long bookId) {
        if (bookId == null) {
            throw new IllegalArgumentException("bookId must not be null");
        }
        loanHistories.add(UserLoanHistory.create(this, bookId));
    }

    public void returnBook(Long bookId) {
        if (bookId == null) {
            throw new IllegalArgumentException("bookId must not be null");
        }

        UserLoanHistory history = loanHistories.stream()
                .filter(each -> each.getBookId().equals(bookId))
                .filter(UserLoanHistory::isNotReturned)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "active loan history is not found, bookId = " + bookId + ", userId = " + id));

        history.markReturned();
    }
}
