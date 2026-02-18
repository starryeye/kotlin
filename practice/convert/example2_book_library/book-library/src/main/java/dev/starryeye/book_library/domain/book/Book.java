package dev.starryeye.book_library.domain.book;

import dev.starryeye.book_library.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "books")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = AuditingEntityListener.class)
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bookname;

    private Long loanedUserId;

    @Version
    private Long version;

    @Builder
    private Book(Long id, String bookname, Long loanedUserId, Long version) {
        if (bookname == null || bookname.isBlank()) {
            throw new IllegalArgumentException("bookname must not be blank");
        }

        this.id = id;
        this.bookname = bookname;
        this.loanedUserId = loanedUserId;
        this.version = version;
    }

    public static Book createBook(String bookname) {
        return Book.builder()
                .id(null)
                .bookname(bookname)
                .loanedUserId(null)
                .version(0L)
                .build();
    }

    public void loanTo(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId must not be null");
        }
        if (loanedUserId != null) {
            throw new IllegalStateException("book is already loaned, id = " + id);
        }

        this.loanedUserId = userId;
    }
}
