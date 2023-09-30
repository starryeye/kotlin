package dev.practice.todo.api.controller;

import dev.practice.todo.api.controller.request.TodoRequest;
import dev.practice.todo.api.controller.response.TodoListResponse;
import dev.practice.todo.api.controller.response.TodoResponse;
import dev.practice.todo.api.service.TodoService;
import dev.practice.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public TodoListResponse getTodoAll() {

        List<Todo> todoAll = todoService.getTodoAll();

        return TodoListResponse.of(todoAll);
    }

    @GetMapping("{id}")
    public TodoResponse getTodo(@PathVariable("id") Long id) {

        Todo todo = todoService.getTodo(id);

        return TodoResponse.of(todo);
    }

    @PostMapping
    public TodoResponse createTodo(@RequestBody TodoRequest request) {

        Todo todo = todoService.createTodo(request);

        return TodoResponse.of(todo);
    }

    @PutMapping("{id}")
    public TodoResponse updateTodo(
            @PathVariable("id") Long id,
            @RequestBody TodoRequest request
    ) {

        Todo todo = todoService.updateTodo(id, request);

        return TodoResponse.of(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Long id) {

        todoService.deleteTodo(id);

        return ResponseEntity.noContent().build();
    }
}
