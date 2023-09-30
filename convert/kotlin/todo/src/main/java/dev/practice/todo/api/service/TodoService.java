package dev.practice.todo.api.service;

import dev.practice.todo.api.controller.request.TodoRequest;
import dev.practice.todo.domain.Todo;
import dev.practice.todo.domain.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<Todo> getTodoAll() {
        return todoRepository.findAll(
                Sort.by(Sort.Direction.DESC, "id")
        );
    }

    public Todo getTodo(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

    @Transactional
    public Todo createTodo(TodoRequest request) {
        Assert.notNull(request, "TodoRequest is null");

        Todo todo = Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .done(false)
                .createdAt(LocalDateTime.now())
                .build();

        return todoRepository.save(todo);
    }


    @Transactional
    public Todo updateTodo(Long id, TodoRequest request) {
        Assert.notNull(request, "TodoRequest is null");

        Todo todo = getTodo(id);

        todo.update(
                request.getTitle(),
                request.getDescription(),
                request.getDone()
        );

        return todo;
    }

    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
