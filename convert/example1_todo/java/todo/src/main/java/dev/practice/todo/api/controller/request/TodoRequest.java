package dev.practice.todo.api.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoRequest {

    private String title;
    private String description;
    private Boolean done;

    @Builder
    private TodoRequest(String title, String description, Boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
    }
}
