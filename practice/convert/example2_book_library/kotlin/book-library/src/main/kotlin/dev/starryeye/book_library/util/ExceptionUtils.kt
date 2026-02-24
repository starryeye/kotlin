package dev.starryeye.book_library.util

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun fail(msg: String): Nothing {
    throw IllegalArgumentException(msg)
}

// 기존 Java CrudRepository.java 의 findById 메서드의 리턴타입이 Optional 인데..
// Kotlin 에서는 Optional 을 elvis 로 대체 할 수 있어서 확장함수인 findByIdOrNull 을 사용한다. (CrudRepositoryExtensions.kt)
// 그 것을 한번더 확장하여 더욱 편리하게 사용하도록 아래의 함수를 만듬..
fun <T : Any, ID : Any> CrudRepository<T, ID>.findByIdOrThrow(id: ID, msg: String): T {
    return this.findByIdOrNull(id) ?: fail(msg)
}