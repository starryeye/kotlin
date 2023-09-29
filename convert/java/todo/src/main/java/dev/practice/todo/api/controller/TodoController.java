package dev.practice.todo.api.controller;

import dev.practice.todo.api.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public void getTodoAll() {

    }

    @GetMapping("{id}")
    public void getTodo(@PathVariable("id") Long id) {

    }

    @PostMapping
    public void createTodo() {

    }

    @PutMapping("{id}")
    public void updateTodo(@PathVariable("id") Long id) {

    }

    @DeleteMapping
    public void deleteTodo() {

    }
}
