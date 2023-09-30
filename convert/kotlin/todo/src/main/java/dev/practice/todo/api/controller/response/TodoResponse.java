package dev.practice.todo.api.controller.response;

import dev.practice.todo.domain.Todo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
public class TodoResponse {

    private Long id;
    private String title;
    private String description;
    private Boolean done;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    private TodoResponse(Long id, String title, String description, Boolean done, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static TodoResponse of(Todo todo) {
        Assert.notNull(todo, "Todo is null");

        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .done(todo.getDone())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }
}
