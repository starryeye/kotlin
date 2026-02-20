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

    @Builder
    private Book(Long id, String bookname) {
        if (bookname == null || bookname.isBlank()) {
            throw new IllegalArgumentException("bookname must not be blank");
        }

        this.id = id;
        this.bookname = bookname;
    }

    public static Book createBook(String bookname) {
        return Book.builder()
                .id(null)
                .bookname(bookname)
                .build();
    }
}
