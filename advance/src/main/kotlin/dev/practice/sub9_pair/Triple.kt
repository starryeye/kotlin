package dev.practice.sub9_pair

/**
 * Pair 에 이어 Triple 도 존재한다.
 *
 * 역시.. data class 로..
 * -> 불변객체이다.
 * -> equals, hashcode, toString, componentN, copy 등의 함수를 컴파일러가 자동으로 생성해준다.
 */

fun main() {
    val triple = Triple("A", "B", 1)
    println(triple)
    val toList = triple.toList()
    println(toList)

    val component3: Int = triple.component3()
    println(component3)
}