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

    /**
     * 할일 리스트 도메인 객체
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;
    private Boolean done; // 할 일이 끝났는지
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    private Todo(Long id, String title, String description, Boolean done, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(String title, String description, Boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.updatedAt = LocalDateTime.now();
    }
}
