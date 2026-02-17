package dev.practice.todo.api.controller

import dev.practice.todo.api.controller.request.TodoRequest
import dev.practice.todo.api.controller.response.TodoListResponse
import dev.practice.todo.api.controller.response.TodoResponse
import dev.practice.todo.api.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping
    fun getTodoAll() =
        TodoListResponse.of(todoService.getTodoAll())

    @GetMapping("/{id}")
    fun getTodo(@PathVariable id: Long) =
        TodoResponse.of(todoService.getTodo(id))

    @PostMapping
    fun createTodo(@RequestBody todoRequest: TodoRequest) =
        TodoResponse.of(todoService.createTodo(todoRequest))

    @PutMapping("/{id}")
    fun updateTodo(
        @PathVariable id: Long,
        @RequestBody request: TodoRequest
    ) = TodoResponse.of(todoService.updateTodo(id, request))

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: Long) : ResponseEntity<Unit> {
        todoService.deleteTodo(id)
        return ResponseEntity.noContent().build()
    }
}