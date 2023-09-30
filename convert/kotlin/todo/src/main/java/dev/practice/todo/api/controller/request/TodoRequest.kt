package dev.practice.todo.api.controller.request

data class TodoRequest(
    val title: String,
    val description: String,
    val done: Boolean,
)