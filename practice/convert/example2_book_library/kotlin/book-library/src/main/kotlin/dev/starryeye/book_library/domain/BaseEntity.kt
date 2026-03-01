package dev.starryeye.book_library.domain

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity {

    @field:CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDate: LocalDateTime? = null
        protected set

    @field:LastModifiedDate
    @Column(nullable = false)
    var lastModifiedDate: LocalDateTime? = null
        protected set
}