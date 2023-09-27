package dev.practice.sub13_functionalprogramming

/**
 * [2]
 *
 * 고차 함수 (High Order Function)
 * 함수를 인자로 받거나 함수를 리턴해주는 함수를 고차 함수라고 한다.
 *
 * 코틀린에서는 함수가 일급 객체이기 때문에..
 * 고차함수를 만들 수 있다.
 */

// 고차 함수
// 인자로 함수를 받는 중이다. 파라미터 이름은 action 이고 타입은 (String) -> Unit 이다.
fun forEach(collection: Collection<String>, action: (String) -> Unit) {
    for(item in collection) {
        action(item) // 인자로 받은 컬렉션의 요소 하나하나를 함수(action) 인자로 넘겨주고 실행시키고 있다.
    }
}

fun doNothing(block: (String) -> Unit) { }

fun main() {

    val listOf = listOf("A", "B", "C")

    val printStr: (String) -> Unit = { println(it) } // 함수 타입의 변수에 람다식(구현) 을 넣었다. -> lambdaExpression.kt

    forEach(listOf, printStr) // 함수를 인자로 전달.. forEach 함수는 함수를 인자로 받는 고차함수이다.

    //--

    doNothing(printStr) // doNothing 은 함수를 인자로 받는 고차함수이다. 내부에 아무것도 안하기 때문에 아무것도 안함



    /**
     * 이제 코틀린에서 제공하는 forEach, map 등의 함수들이 이해가 될 것이다..
     */
    // public inline fun <T> Iterable<T>.forEach(action: (T) -> Unit): Unit
    // -> 함수를 인자로 받는 함수이다. 고차 함수이다.
    listOf.forEach(printStr)
    // 위와 같다
    listOf.forEach({ println(it) })

    // public inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R>
    // -> 함수를 인자로 받는 함수이다. 고차 함수이다.
    val toUppercase: (String) -> String = { it.uppercase() }
    val uppercased = listOf.map(toUppercase)
    uppercased.forEach(printStr) //uppercased 인자 출력



    // 의문 사항..
    // 아래는 뭐징.. 소괄호를 생략할 수 있네..
    // public inline fun <T> Iterable<T>.forEach(action: (T) -> Unit): Unit
    listOf.forEach { println(it) }

    //사실 위의 중괄호는 람다 식인게 밝혀졌다. -> LambdaExpression.kt
    // -> 그러나 아직 소괄호가 생략될 수 있는 이유를 모르겠다.
    // -> 지금 forEach 는 함수를 파라미터로 받는 고차 함수이며.. 파라미터를 전달해주려면 소괄호가 필수가 아닌가..?
    // -> PassingTrailingLambda.kt 에서 해결
}