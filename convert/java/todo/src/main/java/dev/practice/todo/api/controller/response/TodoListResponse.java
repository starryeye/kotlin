package dev.practice.todo.api.controller.response;

import dev.practice.todo.domain.Todo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TodoListResponse {

    private List<TodoResponse> items;

    @Builder
    private TodoListResponse(List<TodoResponse> items) {
        this.items = items;
    }

    public static TodoListResponse of(List<Todo> todoList) {

        List<TodoResponse> todoListResponse = todoList.stream()
                .map(TodoResponse::of)
                .toList();

        return TodoListResponse.builder()
                .items(todoListResponse)
                .build();
    }
}
