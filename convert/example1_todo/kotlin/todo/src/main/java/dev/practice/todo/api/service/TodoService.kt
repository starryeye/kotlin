package dev.practice.todo.api.service

import dev.practice.todo.api.controller.request.TodoRequest
import dev.practice.todo.domain.Todo
import dev.practice.todo.domain.TodoRepository
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Transactional(readOnly = true)
@Service
class TodoService(
    private val todoRepository: TodoRepository,
) {

    fun getTodoAll() =
        todoRepository.findAll(
            Sort.by(Sort.Direction.DESC, "id")
        )

    fun getTodo(id: Long) =
        todoRepository.findByIdOrNull(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @Transactional
    fun createTodo(request: TodoRequest?) : Todo{

        checkNotNull(request) { "TodoRequest is null" }

        val todo = Todo(
            title = request.title,
            description = request.description,
            done = request.done,
            createdAt = LocalDateTime.now(),
        )
        return todoRepository.save(todo)
    }

    @Transactional
    fun updateTodo(id: Long, request: TodoRequest?): Todo {

        checkNotNull(request) { "TodoRequest is null" }

        return getTodo(id).let {
            it.update(
                request.title,
                request.description,
                request.done
            )
            it
        }
    }

    @Transactional
    fun deleteTodo(id: Long) =
        todoRepository.deleteById(id)
}