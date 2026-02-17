package dev.practice.todo.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import java.time.LocalDateTime

//https://multifrontgarden.tistory.com/272
@Entity
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String,

    @Lob
    var description: String,
    var done: Boolean,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime? = null,
) {
    fun update(title: String, description: String, done: Boolean) {
        this.title = title
        this.description = description
        this.done = done
        this.updatedAt = LocalDateTime.now()
    }
}