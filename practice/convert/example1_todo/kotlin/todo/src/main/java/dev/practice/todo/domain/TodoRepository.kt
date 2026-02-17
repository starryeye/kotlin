package dev.practice.todo.domain

import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {

    fun findAllByDoneIsFalseOrderByIdDesc() : List<Todo>? // optional 대신 ? 쓰면됨
}