package dev.practice.todo.api.controller.response

import dev.practice.todo.domain.Todo

data class TodoListResponse(
    val items: List<TodoResponse> //private 가 필요가 없는듯.. val 로 하여 setter 를 없앰
) {

    companion object{ // for static
        fun of(todoList: List<Todo>) =
            TodoListResponse(todoList.map(TodoResponse::of))
    }
}