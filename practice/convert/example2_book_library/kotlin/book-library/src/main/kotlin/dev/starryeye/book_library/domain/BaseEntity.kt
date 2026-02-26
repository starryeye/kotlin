package dev.starryeye.book_library.domain

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
    val createdDate: LocalDateTime? = null

    @field:LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null

}