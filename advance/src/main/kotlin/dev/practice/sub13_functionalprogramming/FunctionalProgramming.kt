package dev.practice.sub13_functionalprogramming

/**
 * [1]
 * 
 * 함수형 프로그래밍
 *
 * 함수형 프로그래밍은 수학의 함수적 개념을 참고하여 만든 패러다임이다.
 *
 * 함수형 패러다임은 부수효과가 없고
 * 똑같은 input 이 들어오면 항상 동일한 output 을 내놓는 순수함수의 개념을 기반으로 한다.
 * 람다, 고차함수, 커리, 메모이제이션, 모나드 등의 개념을 포함한다.
 *
 *
 * 코틀린에서는 함수가 일급객체에 해당한다.
 * - 변수에 할당할 수 있어야한다.
 * - 객체의 인자로 넘길수 있어야한다.
 * - 객체의 리턴 값으로 리턴할 수 있어야한다.
 *
 *
 */

fun main() {

    // 코틀린에서 함수는 일급객체에 해당한다.
    // 변수에 할당할 수 있어야한다.

    // type 이 "() -> Unit" 인 함수를 변수에 할당하였다. (일급 객체 조건)
    val printHello: () -> Unit = { println("Hello") }

    // 주의 사항
    // 아래는 함수의 리턴 값을 변수에 할당한 것이지.. 함수를 변수에 할당한 것이 아니다.. 헷갈리지 말자..
    val printSomething = printGood()

    // 주의 사항
    // fun 으로 선언된 이름있는 함수는 값으로 다룰 수 없다. (printHello 처럼.. 이름 없는 함수(코드블럭)만 가능한듯..)
    // -> 일급 객체의 특성이 없는 것..
//    val printSomething2: () -> Unit = printGood // compile error
    // <참고>
    // fun 으로 선언 되었지만.. 아래의 getFunction, getFunction2 처럼 코드 블럭 자체를 리턴하는 함수는 위와 같은 상황이 아니라
    // 함수를 리턴해주는 함수를 실행한 것이다.

    // type 이 "(String) -> Unit" 인 함수를 변수에 할당하였다. (일급 객체 조건)
    val printMessage : (String) -> Unit = { message ->  println(message) }

    // 아래와 같이 수신자객체(?) 를 생략하면 it 로 사용가능
    val printMessage2 : (String) -> Unit = { println(it) }


    // -------------------------------------------------------------------------
    // 코틀린에서 함수는 일급객체에 해당한다.
    // 객체의 인자로 넘길수 있어야한다.

    // mutableListOf 인자로 함수를 넘겼다. (일급 객체 조건)
    val list = mutableListOf(printHello)
    list[0]() // list 의 0 번째 인자에 access 하였고 "()" 로 함수(인자)를 호출하였다.

    // call 함수의 인자로 함수를 넘겼다. (일급 객체 조건)
    call(printHello)


    // -------------------------------------------------------------------------
    // 코틀린에서 함수는 일급객체에 해당한다.
    // 객체의 리턴 값으로 리턴할 수 있어야한다.

    // 49 ~ 51 에서 수행한 코드를 다시한번 보면..
    // 사실 함수를 리턴 받고 있는 코드가 있다.
    val function: () -> Unit = list[0] // list[0] 을 수행하면 () -> Unit 타입의 인자를 반환한다.(일급 객체 조건) 해당 인자를 변수에 할당.
    function() // 변수에 담긴 함수를 실행한다.

    val printWorld = getFunction() // 함수를 리턴 받았다. (일급 객체 조건)
    printWorld()

    val printWorld2 = getFunction2() // 함수를 리턴 받았다. (일급 객체 조건)
    printWorld2("world2")
}

//함수를 인자로 받아서 그 함수를 실행해주는 함수이다.
fun call(block: () -> Unit) {
    block() // 타입이 () -> Unit 인 인자(함수) 를 "()" 로 호출하였다.
}

fun getFunction () : () -> Unit {
    return { println("world") }
}

fun getFunction2() : (String) -> Unit = { println(it) }

fun printGood() {
    println("Good")
}