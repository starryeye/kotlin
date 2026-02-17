package dev.starryeye.book_library.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    private Integer age;

    @Builder
    private User(Long id, String username, Integer age) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }

        this.id = id;
        this.username = username;
        this.age = age;
    }

    public static User createUser(String username, int age) {
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
}
