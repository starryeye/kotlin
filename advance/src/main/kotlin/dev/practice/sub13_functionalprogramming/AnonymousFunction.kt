package dev.practice.sub13_functionalprogramming

/**
 * [3]
 *
 * 이름 없는 함수를 익명함수 라고 한다.
 */

// 함수 이름은 outerFunc 이고
// 파라미터는 없고
// () -> Unit 타입인 함수를 리턴한다.
fun outerFunc(): () -> Unit {

    // 리턴 으로 함수를 리턴하고 있다.
    // fun 다음에 이름이 와야하는데 이름이 없다.. (-> 익명함수)
    // 파라미터는 없으며 중괄호로 내부 로직이 정의되어있다.
    return fun() {
        println("익명 함수")
    }
}

// outerFunc 와 같은 의미
fun outerFunc2(): () -> Unit = fun() { println("익명 함수") }

fun main() {

    outerFunc()() // outerFunc 를 실행("첫번째 ()") 하고 리턴 값인 함수를 실행("두번째 ()") 한다.
}