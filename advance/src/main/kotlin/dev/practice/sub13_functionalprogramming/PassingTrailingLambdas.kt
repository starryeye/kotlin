package dev.practice.sub13_functionalprogramming

/**
 * 파라미터로 함수를 받는 고차함수인데..
 * 파라미터의 마지막 인자가 함수인 경우 passing trailing lambda (후행 람다 전달) 를 쓸 수 있다.
 *
 * 람다식 내부에서 전달 받는 파라미터가 1개 일 경우 "it" 으로 사용이 가능하다.
 */

// 고차함수(forEachCollection) 의 마지막 파라미터가 함수이다.
fun forEachCollection(collection: Collection<String>, action: (String) -> Unit) {
    for(item in collection) {
        action(item) // 인자로 받은 컬렉션의 요소 하나하나를 함수(action) 인자로 넘겨주고 실행시키고 있다.
    }
}

fun main() {

    val listOf = listOf("A", "B", "C")

    forEachCollection(listOf, { println(it) }) // 람다 식을 인자로 넘겨줌

    // passing trailing lambda 문법 사용
    forEachCollection(listOf) {
        println(it)
    }

    // 지금까지 사용해왔던 모든 코드의 큰 의문점이 해결된다.
    // 이걸 이제야 알려주네..

    listOf.forEach(fun(str: String) { println(str) }) // 익명함수
    listOf.forEach( { println(it) }) // 람다 식 사용
    listOf.forEach { // 람다 식 사용, passing trailing lambda 문법 사용
        println(it)
    }
    listOf.forEach(System.out::println) // TODO 자바의 메서드 레퍼런스... 도 가능하네..?


    // --------------------------------------

    // 람다식 내부에서 전달 받는 파라미터가 1개 일 경우 "it" 으로 사용이 가능하다.
    val filter = listOf.filter {
        it.startsWith("A")
    }
    println(filter)
}