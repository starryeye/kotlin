package dev.practice.todo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;
    private Boolean done;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    private Todo(String title, String description, Boolean done, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //TODO create

    public void update(String title, String description, Boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.updatedAt = LocalDateTime.now();
    }
}
