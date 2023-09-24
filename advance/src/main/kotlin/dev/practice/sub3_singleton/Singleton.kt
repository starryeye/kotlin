package dev.practice.sub3_singleton

import java.time.LocalDateTime

/**
 * 코틀린에서의 싱글톤
 *
 * object 키워드를 사용하면 손쉽게 구현가능
 */

object Singleton {
    val a = 123

    fun printA() = println(a)
}

object DatetimeUtils {
    val now: LocalDateTime
        get() = LocalDateTime.now()

    const val DEFAULT_FORMAT = "YYYY-MM-DD"

    fun same(a: LocalDateTime, b: LocalDateTime) : Boolean {
        return a == b
    }
}

fun main() {
    val singleton: Singleton = Singleton

    println(Singleton.a)
    Singleton.printA()
    singleton.printA()

    println(DatetimeUtils.now)
    println(DatetimeUtils.now)
    println(DatetimeUtils.now)

    println(DatetimeUtils.DEFAULT_FORMAT)

    val now = LocalDateTime.now()
    println(DatetimeUtils.same(now, now))
}