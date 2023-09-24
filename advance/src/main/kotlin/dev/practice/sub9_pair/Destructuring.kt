package dev.practice.sub9_pair

/**
 * 코틀린에서 Pair, Triple 을 사용하면 구조분해선언 문법을 사용하기 편하다.
 *
 * 구조분해선언 문법은..
 * 사실 내부적으로는.. 코틀린 컴파일러가 componentN 을 사용한다.
 * -> decompile 참고
 *
 * 사실 다른 자료구조에서도 사용가능하다.
 * -> 이 말은 예를 들면 list 도 componentN 을 가진다는 의미이다.
 * -> 단, list 의 경우 component 1 에서 5 까지만 제공된다.
 */

fun main() {
    //원래라면 first, second, third 혹은 componentN 을 사용하여 하나하나 접근을 해줘야했지만..
    // 아래와 같이 구조분해선언 문법을 통해 한번에 가져올 수 있는 문법을 사용할 수 있다.
    val (a: String, b: String, c: Int) = Triple("A", "B", 1) // 타입은 생략가능하다.

    println("$a and $b and $c")

    // ------------------------------------------------------

    val listOf: List<Comparable<*>> = listOf("A", "B", "C", 1)
    val (a1, b1, c1, d1) = listOf //list 도 구조 분해 선언 문법을 사용할 수 있다.

    println("$a1 and $b1 and $c1 and $d1")

    // ------------------------------------------------------

    //k: A, v: 1
    // 사실 to 도 내부에 보면 Pair 이다
    // 따라서, Pair("A", 1) 로 치환할 수 있다.
    val mutableMap = mutableMapOf("A" to 1)

    for ((k, v) in mutableMap) {
        println("Key : $k, Value: $v") // for loop 에서도 편리하게 사용가능하다.
    }

    // 아래는 개인적으로 더 알아보기..

    for (entry: MutableMap.MutableEntry<String, Int> in mutableMap) {
        println("Key : ${entry.key}, Value: ${entry.value}")
    }

    mutableMap.forEach(
        System.out::println
    )

    mutableMap.forEach {
        println("key: ${it.key}, value: ${it.value}")
    }

    //TODO 소괄호와 중괄호 쓰임 차이..??
}