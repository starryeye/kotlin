package dev.practice.sub9_pair

/**
 * 아래와 같이 사용 할 수 도 있지만..
 * fun plus(a: Int, b: Int) = a + b
 *
 * C++ 의 Pair 의 개념이 코틀린에 있다.
 *
 * Pair 는 data class 이다.
 * -> 불변객체이다.
 * -> equals, hashcode, toString, componentN, copy 등의 함수를 컴파일러가 자동으로 생성해준다.
 *
 * toList 라는 확장 함수가 존재한다.
 * pair 의 인자들을 불변 리스트의 원소로 넣어 반환한다.
 *
 */
fun plus(pair: Pair<Int, Int>) = pair.first + pair.second

fun main() {

    println(Pair(1, 2))

    val pair = Pair("A", 2)
//    pair.first = "B" // compile error, Pair 는 불변이다.

    val newPair = pair.copy(first = "B") // Pair 는 data class 이므로 copy 를 사용할 수 있다.

    println(newPair.component1()) // componentN 사용

    val toList = newPair.toList() // 불변 리스트로 만들어 반환해주는 확장함수 존재
    println(toList)


}